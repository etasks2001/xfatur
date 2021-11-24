package com.xfatur.exception;

public class LinhaIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LinhaIdNotFoundException(String message) {
	super(message);
    }

}
