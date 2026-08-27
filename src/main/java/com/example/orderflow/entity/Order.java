package com.example.orderflow.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.orderflow.validator.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Customer
    private String customerId;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLine>orderLines = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name="order_status")
    private OrderStatus status=OrderStatus.PENDING;

    public void setCustomerId(String id) {this.customerId = id;}
    public void setOrderLines(List<OrderLine>list) {this.orderLines = list;}

    public String getCustomerId() {return this.customerId;}
    public List<OrderLine>getOrderLines() {return this.orderLines;}
    public Long getId() {return this.id;}

    public void setStatus(OrderStatus st) {this.status = st;}
    public OrderStatus getStatus() {return this.status;}
}