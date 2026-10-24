package com.example.exc.service;

public class EntityAlreadyExistsException extends ServiceException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
