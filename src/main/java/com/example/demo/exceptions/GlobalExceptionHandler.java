package com.example.demo.exceptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> dataNotFound(ProductNotFoundException exception) {

        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("error", exception.getMessage());
        errorInfo.put("errorType", HttpStatus.NOT_FOUND.name());
        errorInfo.put("status", HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }
}
