package com.example.orderflow.exception;

public class InvalidOrderStatusException extends RuntimeException {
    public InvalidOrderStatusException(String msg) {
        super(msg);
    }
}