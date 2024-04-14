package com.example.backendcoreservice.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@Slf4j
@RestControllerAdvice()
public class GlobalExceptionHandler {
    private final ApiResponseBuilder apiResponseBuilder;

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity handleException(EntityNotFoundException e) {
        log.info("Entity not found exception: {}", e.getMessage());
        return new ResponseEntity<>(apiResponseBuilder.buildFailureResponse(e.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e) {
        log.error("Internal server error: {}", e.getMessage());
        return new ResponseEntity<>(apiResponseBuilder.buildFailureResponse(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
