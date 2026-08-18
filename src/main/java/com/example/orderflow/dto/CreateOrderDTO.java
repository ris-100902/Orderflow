package com.example.orderflow.dto;

import java.util.HashMap;
import java.util.Map;

public class CreateOrderDTO {
    private String customerId;
    private Map<String, Integer> items = new HashMap<>();

    public void setCustomerId(String id) {customerId = id;}
    public void setItems(Map<String,Integer>items) {this.items = items;}

    public String getCustomerId() {return this.customerId;}
    public Map<String, Integer>getItems() {return this.items;}
}