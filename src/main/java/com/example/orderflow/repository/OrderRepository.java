package com.example.orderflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orderflow.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}