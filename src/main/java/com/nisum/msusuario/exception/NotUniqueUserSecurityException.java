package com.nisum.msusuario.exception;

public class NotUniqueUserSecurityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotUniqueUserSecurityException() {
        super();
    }

    public NotUniqueUserSecurityException(String message) {
        super(message);
    }
}
