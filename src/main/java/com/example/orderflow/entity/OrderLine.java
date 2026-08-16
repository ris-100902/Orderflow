package com.example.orderflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderItem_id")
    private OrderItem orderItem;

    public void setQuantity(int n) {this.quantity = n;}
    public void setOrderItem(OrderItem item) {this.orderItem = item;}

    public int getQuantity() {return this.quantity;}
    public OrderItem getOrderItem() {return this.orderItem;}
}