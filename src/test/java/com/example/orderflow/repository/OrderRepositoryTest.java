package com.example.orderflow.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.orderflow.entity.Order;
import com.example.orderflow.entity.OrderItem;
import com.example.orderflow.entity.OrderLine;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void customerIdSyntax() {

        OrderItem pen = new OrderItem();
        pen.setName("Pen");

        OrderLine ol = new OrderLine();
        ol.setOrderItem(pen);
        ol.setQuantity(2);

        Order o = new Order();
        o.setCustomerId("1234");
        o.setOrderLines(List.of(ol));

        ConstraintViolationException ex = assertThrows(
            ConstraintViolationException.class, () -> orderRepository.saveAndFlush(o)
        );

        assertTrue(ex.getMessage().contains("CustomerId should start with \"cust-\" followed by integers"));
    }
}