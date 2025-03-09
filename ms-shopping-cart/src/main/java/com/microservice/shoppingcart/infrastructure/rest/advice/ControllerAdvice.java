package com.microservice.shoppingcart.infrastructure.rest.advice;

import com.microservice.shoppingcart.application.dto.response.ExceptionResponseDTO;
import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.exception.UnmodifiableFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponseDTO handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        return ExceptionResponseDTO.builder()
                .code(String.valueOf(HttpStatus.NOT_FOUND))
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnmodifiableFieldException.class)
    public ExceptionResponseDTO handleUnmodifiableFieldException(UnmodifiableFieldException e) {
        log.error(e.getMessage());
        return ExceptionResponseDTO.builder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST))
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
