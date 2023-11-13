package com.taller.Exceptions;

public class NoViewsAvailableException extends RuntimeException {
    public NoViewsAvailableException(String message) {
        super(message);
    }
}
