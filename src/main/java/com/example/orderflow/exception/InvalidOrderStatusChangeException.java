package com.example.orderflow.exception;

public class InvalidOrderStatusChangeException extends RuntimeException {
    public InvalidOrderStatusChangeException(String msg) {
        super(msg);
    }
}