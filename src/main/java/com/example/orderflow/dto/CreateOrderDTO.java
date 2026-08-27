package com.example.orderflow.dto;

import java.util.HashMap;
import java.util.Map;

import com.example.orderflow.entity.OrderStatus;

public class CreateOrderDTO {
    private String customerId;
    private Map<String, Integer> items = new HashMap<>();
    private OrderStatus status;

    public void setCustomerId(String id) {customerId = id;}
    public void setItems(Map<String,Integer>items) {this.items = items;}
    public void setStatus(OrderStatus st) {this.status = st;}

    public String getCustomerId() {return this.customerId;}
    public Map<String, Integer>getItems() {return this.items;}
    public OrderStatus getStatus() {return this.status;}
}