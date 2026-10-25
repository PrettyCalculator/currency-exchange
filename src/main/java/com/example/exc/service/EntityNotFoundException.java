package com.example.exc.service;

public class EntityNotFoundException extends ServiceException {
    private static final int CODE = 404;

    public EntityNotFoundException(String message) {
        super(CODE, message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
