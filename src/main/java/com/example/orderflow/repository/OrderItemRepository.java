package com.example.orderflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.orderflow.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByName(String name);
}