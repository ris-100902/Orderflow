package com.example.orderflow.dto;

import java.util.HashMap;
import java.util.Map;

public class OrderDTO {
    private String customerId;
    private Map<String, Integer> items = new HashMap<>();

    public String getCustomerId() {return this.customerId;}
    public Map<String, Integer>getItems() {return this.items;}
}