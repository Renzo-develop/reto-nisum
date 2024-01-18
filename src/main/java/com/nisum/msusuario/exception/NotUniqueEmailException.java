package com.nisum.msusuario.exception;

public class NotUniqueEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotUniqueEmailException() {
        super();
    }

    public NotUniqueEmailException(String message) {
        super(message);
    }
}
