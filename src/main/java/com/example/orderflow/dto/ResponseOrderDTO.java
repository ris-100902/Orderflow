package com.example.orderflow.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.orderflow.entity.OrderStatus;

public class ResponseOrderDTO {
    private String customerId;
    private List<OrderLineDTO>items = new ArrayList<>();
    private OrderStatus status;

    public void setCustomerId(String id) {this.customerId = id;}
    public void setItems(List<OrderLineDTO>list) {this.items = list;}
    public void setStatus(OrderStatus st) {this.status = st;}

    public String getCustomerId() {return customerId;}
    public List<OrderLineDTO>getItems() {return items;}
    public OrderStatus getStatus() {return this.status;}
}