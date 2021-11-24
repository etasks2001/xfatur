package com.xfatur.exception;

public class PaisIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaisIdNotFoundException(String message) {
	super(message);
    }

}
