package com.xfatur.exception;

public class TipoIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TipoIdNotFoundException(String message) {
	super(message);
    }

}
