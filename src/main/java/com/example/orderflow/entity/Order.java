package com.example.orderflow.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String customerId;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLine>orderLines = new ArrayList<>();

    public void setCustomerId(String id) {this.customerId = id;}
    public void setOrderLines(List<OrderLine>list) {this.orderLines = list;}

    public String getCustomerId() {return this.customerId;}
    public List<OrderLine>getOrderLines() {return this.orderLines;}
    public Long getId() {return this.id;}
}