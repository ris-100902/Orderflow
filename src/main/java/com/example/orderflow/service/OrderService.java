package com.example.orderflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderflow.dto.OrderDTO;
import com.example.orderflow.entity.Order;
import com.example.orderflow.entity.OrderItem;
import com.example.orderflow.entity.OrderLine;
import com.example.orderflow.exception.ResourceNotFoundException;
import com.example.orderflow.repository.OrderItemRepository;
import com.example.orderflow.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

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

    public Order createOrder(OrderDTO dto) {
        Order newOrder = new Order();
        newOrder.setCustomerId(dto.getCustomerId());
        List<OrderLine> orderLines = new ArrayList<>();
        Map<String,Integer> map = dto.getItems();
        
        for (String product: map.keySet()) {
            OrderLine newLine = new OrderLine();
            OrderItem item = orderItemRepository.findByName(product);
            newLine.setOrderItem(item);
            newLine.setQuantity(map.get(product));
            orderLines.add(newLine);
        }
        newOrder.setOrderLines(orderLines);
        orderRepository.save(newOrder);
        
        IO.println(newOrder.getCustomerId());
        for (OrderLine l: newOrder.getOrderLines()) {
            IO.println("OrderLine : " + l.getQuantity() + " " + l.getOrderItem().getName());
        }
        return newOrder;
    }
}