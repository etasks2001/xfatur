package com.xfatur.exception;

public class ProdutoIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoIdNotFoundException(String message) {
	super(message);
    }

}
