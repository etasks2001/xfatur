package com.xfatur.exception;

public class UsuarioIdNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsuarioIdNotFoundException(String message) {
	super(message);
    }

}
