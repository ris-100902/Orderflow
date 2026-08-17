package com.example.orderflow.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerValidation implements ConstraintValidator<Customer, String> {
    @Override
    public void initialize (Customer cust){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches("cust-\\d+");
    }
}