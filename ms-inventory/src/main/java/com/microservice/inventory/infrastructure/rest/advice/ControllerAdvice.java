package com.microservice.inventory.infrastructure.rest.advice;

import com.microservice.inventory.application.dto.response.ExceptionResponseDTO;
import com.microservice.inventory.application.exception.ProductNotFoundException;
import com.microservice.inventory.domain.exception.InsufficientStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(InsufficientStockException.class)
    public ExceptionResponseDTO handleInsufficientStockException(InsufficientStockException e) {
        log.error(e.getMessage());
        return ExceptionResponseDTO.builder()
                .code(String.valueOf(HttpStatus.CONFLICT))
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ExceptionResponseDTO handleProductNotFoundException(ProductNotFoundException e) {
        log.error(e.getMessage());
        return ExceptionResponseDTO.builder()
                .code(String.valueOf(HttpStatus.NOT_FOUND))
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
