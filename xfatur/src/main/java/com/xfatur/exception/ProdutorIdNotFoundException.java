package com.xfatur.exception;

public class ProdutorIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutorIdNotFoundException(String message) {
	super(message);

    }

}
