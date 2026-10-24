package com.example.exc.dao;

public class ForeignKeyViolationException extends DaoException{
    public ForeignKeyViolationException(String message) {
        super(message);
    }

    public ForeignKeyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
