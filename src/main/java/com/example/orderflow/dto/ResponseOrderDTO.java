package com.example.orderflow.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseOrderDTO {
    private String customerId;
    private List<OrderLineDTO>items = new ArrayList<>();

    public void setCustomerId(String id) {this.customerId = id;}
    public void setItems(List<OrderLineDTO>list) {this.items = list;}

    public String getCustomerId() {return customerId;}
    public List<OrderLineDTO>getItems() {return items;}
}