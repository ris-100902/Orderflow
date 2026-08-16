package com.example.orderflow.dto;

public class OrderLineDTO {
    private String product;
    private int quantity;

    public void setProduct(String product) {this.product = product;}
    public void setQuantity(int q) {this.quantity = q;}

    public String getProduct() {return this.product;}
    public int getQuantity() {return quantity;}
}