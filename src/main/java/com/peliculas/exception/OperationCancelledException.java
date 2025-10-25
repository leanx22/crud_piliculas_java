package com.peliculas.exception;

public class OperationCancelledException extends Exception {
    public OperationCancelledException() {}
    public OperationCancelledException(String message) {
        super(message);
    }
}
