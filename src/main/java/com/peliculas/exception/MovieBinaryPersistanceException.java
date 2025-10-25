package com.peliculas.exception;

import java.io.IOException;

///Exception produced by failed or interrupted attempt of saving/loading the movieList binary file.
public class MovieBinaryPersistanceException extends IOException {

    public MovieBinaryPersistanceException(){};

    public MovieBinaryPersistanceException(String message) {super(message);}

}
