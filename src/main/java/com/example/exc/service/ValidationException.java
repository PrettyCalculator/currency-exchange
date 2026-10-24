package com.example.exc.service;

public class ValidationException extends ServiceException {
    public ValidationException(String message) {
        super(message);
    }
}
