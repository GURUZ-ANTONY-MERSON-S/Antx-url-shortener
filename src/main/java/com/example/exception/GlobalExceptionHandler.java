package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleShortUrlNotFound(
            ShortUrlNotFoundException ex) {

        Map<String, Object> error = new HashMap<>();

        error.put("error", ex.getMessage());
        error.put("status", 404);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}