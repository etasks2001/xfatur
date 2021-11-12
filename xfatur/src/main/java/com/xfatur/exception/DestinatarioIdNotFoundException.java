package com.xfatur.exception;

public class DestinatarioIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DestinatarioIdNotFoundException(String message) {
	super(message);
    }

}
