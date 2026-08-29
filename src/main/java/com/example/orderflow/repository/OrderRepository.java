package com.example.orderflow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderflow.entity.Order;
import com.example.orderflow.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
    Page<Order> findByCustomerId(String id, Pageable pageable);
    Page<Order> findByStatusAndCustomerId(OrderStatus status, String id, Pageable pageable);
}