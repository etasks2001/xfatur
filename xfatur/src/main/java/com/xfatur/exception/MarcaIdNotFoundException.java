package com.xfatur.exception;

public class MarcaIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MarcaIdNotFoundException(String message) {
	super(message);
    }

}
