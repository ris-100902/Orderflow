package com.example.orderflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderflow.entity.OrderItem;
import com.example.orderflow.service.OrderService;



@RestController
@RequestMapping("/api/v1/orderItems")
public class OrderItemController {

    private final OrderService orderItemService;

    public OrderItemController(OrderService os) {
        this.orderItemService = os;
    }

    @GetMapping
    public List<OrderItem> getAll() {
        return this.orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getById(@PathVariable Long id) {
        return this.orderItemService.getOrderItemById(id);
    }
    

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem item) {
        orderItemService.createOrderItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderItem> deleteOrderItem(@PathVariable long id) {
        OrderItem item = orderItemService.deleteOrderItemById(id);
        return new ResponseEntity<>(item, HttpStatus.NO_CONTENT);
    }
}