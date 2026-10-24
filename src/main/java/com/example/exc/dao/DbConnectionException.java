package com.example.exc.dao;

public class DbConnectionException extends DbException {
    public DbConnectionException(String message) {
        super(message);
    }

    public DbConnectionException(String message, Throwable cause) {
        super(message, cause);
    }


}
