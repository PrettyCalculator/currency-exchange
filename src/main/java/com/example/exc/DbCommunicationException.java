package com.example.exc;


public class DbCommunicationException extends DbException {
    public DbCommunicationException(String message) {
        super(message);
    }

    public DbCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
