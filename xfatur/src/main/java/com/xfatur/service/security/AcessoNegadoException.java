package com.xfatur.service.security;

@SuppressWarnings("serial")
public class AcessoNegadoException extends RuntimeException {

    public AcessoNegadoException(String message) {
	super(message);
    }

}
