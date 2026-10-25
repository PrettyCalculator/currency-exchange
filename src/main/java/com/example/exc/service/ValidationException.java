package com.example.exc.service;

public class ValidationException extends ServiceException {
    private static final int CODE = 400;

    public ValidationException(String message) {
        super(CODE, message);
    }

    public ValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
