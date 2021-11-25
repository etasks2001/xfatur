package com.xfatur.exception;

public class ProdutoEstoqueInsuficienteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoEstoqueInsuficienteException(String message) {
	super(message);
    }

}
