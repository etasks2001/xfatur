package com.xfatur.exception;

public class EstoqueMensalIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EstoqueMensalIdNotFoundException(String message) {
	super(message);
    }

}
