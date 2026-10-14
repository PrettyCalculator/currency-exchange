package com.example.exc;

public class DbException extends DaoException {
    public DbException(String message) {
        super(message);
    }
    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

}
