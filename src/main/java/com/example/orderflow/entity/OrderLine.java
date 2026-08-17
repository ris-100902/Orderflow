package com.example.orderflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="quantity cannot be empty")
    @Min(1)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    public void setQuantity(int n) {this.quantity = n;}
    public void setOrderItem(OrderItem item) {this.orderItem = item;}

    public int getQuantity() {return this.quantity;}
    public OrderItem getOrderItem() {return this.orderItem;}

    public Long getId() {
        return id;
    }
}