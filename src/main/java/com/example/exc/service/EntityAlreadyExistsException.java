package com.example.exc.service;

public class EntityAlreadyExistsException extends ServiceException {
    private static final int CODE = 409;

    public EntityAlreadyExistsException(String message) {
        super(CODE, message);
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
