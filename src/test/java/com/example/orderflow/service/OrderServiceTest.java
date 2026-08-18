package com.example.orderflow.service;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.orderflow.dto.CreateOrderDTO;
import com.example.orderflow.entity.Order;
import com.example.orderflow.entity.OrderItem;
import com.example.orderflow.exception.ResourceNotFoundException;
import com.example.orderflow.repository.OrderItemRepository;
import com.example.orderflow.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder() {
        CreateOrderDTO dto = new CreateOrderDTO();
        dto.setCustomerId("cust-0000");
        dto.setItems(Map.of("Pen",5, "Pencil",2));

        OrderItem pen = new OrderItem();
        OrderItem pencil = new OrderItem();

        pen.setName("Pen");
        pencil.setName("Pencil");

        when(orderItemRepository.findByName("Pen")).thenReturn(Optional.of(pen));
        when(orderItemRepository.findByName(("Pencil"))).thenReturn(Optional.of(pencil));

        Order savedOrder = orderService.createOrder(dto);
        
        // Assert
        assertEquals(savedOrder.getCustomerId(), "cust-0000");
        assertEquals(savedOrder.getOrderLines().size(), 2);

        assertTrue(savedOrder.getOrderLines().stream().anyMatch(line -> line.getQuantity() == 5 && line.getOrderItem()== pen));
        assertTrue(savedOrder.getOrderLines().stream().anyMatch(line -> line.getQuantity() == 2 && line.getOrderItem() == pencil));

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void orderItemShouldExistforOrderCreation() {
        CreateOrderDTO dto = new CreateOrderDTO();
        dto.setCustomerId("cust-1234");
        dto.setItems(Map.of("Pen",5));

        when(orderItemRepository.findByName("Pen")).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> orderService.createOrder(dto));
        assertTrue(ex.getMessage().contains("not Found"));
        verify(orderRepository, never()).save(any(Order.class));
    }
}