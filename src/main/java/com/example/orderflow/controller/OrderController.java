package com.example.orderflow.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderflow.dto.ChangeStatusDTO;
import com.example.orderflow.dto.CreateOrderDTO;
import com.example.orderflow.dto.ResponseOrderDTO;
import com.example.orderflow.entity.Order;
import com.example.orderflow.service.OrderService;



@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
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

    @GetMapping
    public List<ResponseOrderDTO> getOrdersByFilters(
        @RequestParam(required=false) String status,
        @RequestParam(required=false) String customer,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="2") int size
    ) {
        return orderService.getOrders(status, customer, page, size).map(orderService::convertOrderToRes).getContent();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDTO dto) {
        Order createdOrder = orderService.createOrder(dto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseOrderDTO> changeOrderStatus(@PathVariable Long id, @RequestBody ChangeStatusDTO dto) {
        Order fetchedOrder = orderService.changeStatus(id, dto);
        return ResponseEntity.ok().body(orderService.convertOrderToRes(fetchedOrder));
    }
}