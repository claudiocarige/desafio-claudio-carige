package com.claudiocarige.desafioStartDB.services.exceptions;

public class NoSuchElementException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
