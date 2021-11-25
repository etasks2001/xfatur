package com.xfatur.exception;

public class ProdutoReservadoInsuficienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoReservadoInsuficienteException(String message) {
	super(message);
    }
}
