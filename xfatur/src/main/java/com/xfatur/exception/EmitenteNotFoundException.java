package com.xfatur.exception;

public class EmitenteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmitenteNotFoundException(String message) {
	super(message);
    }

}
