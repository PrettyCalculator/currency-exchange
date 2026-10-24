package com.example.exc.dao;

public class DbException extends DaoException {
    public DbException(String message) {
        super(message);
    }
    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

}
