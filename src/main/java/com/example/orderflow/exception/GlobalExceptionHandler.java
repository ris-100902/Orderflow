package com.example.orderflow.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation (ConstraintViolationException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        for (ConstraintViolation<?> violation: ex.getConstraintViolations()) {
            String fieldName = violation.getPropertyPath().toString();
            String location = fieldName.contains("requestBody") ? "body" : "header";

            errorResponse.put("field", fieldName);
            errorResponse.put("location", location);
            errorResponse.put("reason", ex.getMessage());
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors (MethodArgumentNotValidException ex) {
        List<String>errors = ex.getBindingResult()
                                .getFieldErrors().stream()
                                .map(error -> error.getDefaultMessage() !=null ? error.getDefaultMessage() : "Validation Error")
                                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(getErrorsMap(errors));
    }

    private Map<String, List<String>> getErrorsMap(List<String>errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}