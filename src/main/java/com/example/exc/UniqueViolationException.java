package com.example.exc;

public class UniqueViolationException extends DaoException {
    public UniqueViolationException(String message) {
        super(message);
    }
    public UniqueViolationException(String message, Throwable cause) {
        super(message, cause);
    }


}
