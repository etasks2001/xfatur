package com.xfatur.exception;

public class ProdutoCodigoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProdutoCodigoNotFoundException(String message) {
	super(message);
    }

}
