package com.example.exc.service;

public class ServiceException extends RuntimeException {
    private static final int DEFAULT_CODE = 500;

    public final int code;

    public ServiceException(String message) {
        this(DEFAULT_CODE, message);
    }

    public ServiceException(String message, Throwable cause) {
        this(DEFAULT_CODE, message, cause);
    }

    protected ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    protected ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
