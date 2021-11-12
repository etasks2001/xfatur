package com.xfatur.exception;

public class EmitenteIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmitenteIdNotFoundException(String message) {
	super(message);
    }

}
