package com.example.orderflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.orderflow.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}