package com.example.orderflow.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.orderflow.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByName(String name);
}