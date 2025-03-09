package com.microservice.shoppingcart.application.exception;

public class UnmodifiableFieldException extends RuntimeException {
    public UnmodifiableFieldException(String message) {
        super(message);
    }
}
