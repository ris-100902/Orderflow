package com.example.orderflow.controller;

import com.example.orderflow.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderflow.dto.CreateOrderDTO;
import com.example.orderflow.entity.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    private OrderService OrderService;


    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order fetchedOrder = orderService.getOrderById(id);
        return new ResponseEntity<>(fetchedOrder, HttpStatus.OK);
    }
    

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDTO dto) {
        Order createdOrder = orderService.createOrder(dto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    
}