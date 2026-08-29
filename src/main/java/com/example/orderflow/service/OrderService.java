package com.example.orderflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.orderflow.dto.ChangeStatusDTO;
import com.example.orderflow.dto.CreateOrderDTO;
import com.example.orderflow.dto.OrderLineDTO;
import com.example.orderflow.dto.ResponseOrderDTO;
import com.example.orderflow.entity.Order;
import com.example.orderflow.entity.OrderItem;
import com.example.orderflow.entity.OrderLine;
import com.example.orderflow.entity.OrderStatus;
import com.example.orderflow.exception.InvalidOrderStatusException;
import com.example.orderflow.exception.ResourceNotFoundException;
import com.example.orderflow.repository.OrderItemRepository;
import com.example.orderflow.repository.OrderRepository;

@Service
public class OrderService {
    
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderService(OrderItemRepository oir, OrderRepository or) {
        this.orderItemRepository = oir;
        this.orderRepository = or;
    }

    public List<OrderItem>getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public void createOrderItem(OrderItem item) {
        orderItemRepository.save(item);
    }

    public OrderItem getOrderItemById(Long id) {
        OrderItem item = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such orderItem exists"));
        return item;
    }

    public OrderItem deleteOrderItemById(Long id) {
        OrderItem item = getOrderItemById(id);
        orderItemRepository.deleteById(id);
        return item;
    }

    public List<Order>getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Order fetchedOrder = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such Order exists"));
        return fetchedOrder;
    }

    public Order createOrder(CreateOrderDTO dto) {
        Order newOrder = new Order();
        newOrder.setCustomerId(dto.getCustomerId());
        newOrder.setStatus(dto.getStatus() == null ? OrderStatus.PENDING : dto.getStatus());
        List<OrderLine> orderLines = new ArrayList<>();
        Map<String,Integer> map = dto.getItems();
        
        for (String product: map.keySet()) {
            OrderLine newLine = new OrderLine();
            OrderItem item = orderItemRepository.findByName(product).orElseThrow(() -> new ResourceNotFoundException("OrderItem not Found : " + product));
            newLine.setOrderItem(item);
            newLine.setQuantity(map.get(product));
            orderLines.add(newLine);
        }
        newOrder.setOrderLines(orderLines);
        orderRepository.save(newOrder);
        
        return newOrder;
    }

    public ResponseOrderDTO convertOrderToRes(Order order) {
        ResponseOrderDTO dto = new ResponseOrderDTO();
        dto.setCustomerId(order.getCustomerId());
        dto.setStatus(order.getStatus());

        List<OrderLineDTO> l = new ArrayList<>();
        for (OrderLine line : order.getOrderLines()) {
            OrderLineDTO olDto = new OrderLineDTO();
            olDto.setQuantity(line.getQuantity());
            olDto.setProduct(line.getOrderItem().getName());
            l.add(olDto);
        }
        dto.setItems(l);
        return dto;
    }

    private OrderStatus parseStatus(String status) {
        try {
            return OrderStatus.valueOf(status);
        } catch (IllegalArgumentException ex) {
            throw new InvalidOrderStatusException("Invalid order status: " + status);
        }
    }

    public Page<Order> getOrders(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Order::getId));
        if (status==null) {
            return orderRepository.findAll(pageable);
        }
        OrderStatus st = parseStatus(status.toUpperCase());
        return orderRepository.findByStatus(st, pageable);
    }

    public Order changeStatus(Long id, ChangeStatusDTO dto) {
        Order currOrder = getOrderById(id);
        OrderStatus st = parseStatus(dto.getStatus().toUpperCase());
        currOrder.setStatus(st);
        orderRepository.save(currOrder);
        return currOrder;
    }
}