package com.example.orderflow.controller;

import com.example.orderflow.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderflow.dto.CreateOrderDTO;
import com.example.orderflow.dto.ResponseOrderDTO;
import com.example.orderflow.entity.Order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<ResponseOrderDTO> getAllOrders() {
        List<ResponseOrderDTO> list = new ArrayList<>();
        for (Order o: orderService.getAllOrders()) {
            list.add(orderService.convertOrderToRes(o));
        }
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDTO> getOrderById(@PathVariable Long id) {
        Order fetchedOrder = orderService.getOrderById(id);
        ResponseOrderDTO dto = orderService.convertOrderToRes(fetchedOrder);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDTO dto) {
        Order createdOrder = orderService.createOrder(dto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    
}