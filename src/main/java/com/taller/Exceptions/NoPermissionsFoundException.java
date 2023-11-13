package com.taller.Exceptions;

public class NoPermissionsFoundException extends RuntimeException {
    public NoPermissionsFoundException(String message) {
        super(message);
    }
}
