package com.example.backendcoreservice.api;

import com.example.backendcoreservice.exception.CustomException;
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

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity handleException(CustomException e) {
        log.error("Internal server error: {}", e.getMessage());
        return new ResponseEntity<>(apiResponseBuilder.buildFailureResponse(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        log.error("Internal server error: {}", e.getMessage());
        return new ResponseEntity<>(apiResponseBuilder.buildFailureResponse(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
