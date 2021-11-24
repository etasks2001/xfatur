package com.xfatur.exception;

public class OrigemIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrigemIdNotFoundException(String message) {
	super(message);
    }

}
