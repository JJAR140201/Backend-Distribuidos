package com.taller.Exceptions;

public class NoDataAvailableException extends RuntimeException {
    public NoDataAvailableException(String message) {
        super(message);
    }
}