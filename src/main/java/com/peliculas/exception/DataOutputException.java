package com.peliculas.exception;

import java.io.IOException;

///Exception produced by a failed attempt of export any data.
public class DataOutputException extends IOException {
    public DataOutputException() { }
    public DataOutputException(String message) {
        super(message);
    }
}
