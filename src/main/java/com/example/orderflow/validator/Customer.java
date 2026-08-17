package com.example.orderflow.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy=CustomerValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Customer {
    String message() default "{CustomerId should start with \"cust-\" followed by integers }";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}