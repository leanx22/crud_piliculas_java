package com.peliculas.exception;

public class InvalidDirectoryException extends Exception {
    public InvalidDirectoryException() {}
    public InvalidDirectoryException(String message) {
        super(message);
    }
}
