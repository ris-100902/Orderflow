package com.example.orderflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderflow.entity.OrderItem;
import com.example.orderflow.exception.ResourceNotFoundException;
import com.example.orderflow.repository.OrderItemRepository;

@Service
public class OrderService {
    @Autowired
    private OrderItemRepository orderItemRepository;

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
}