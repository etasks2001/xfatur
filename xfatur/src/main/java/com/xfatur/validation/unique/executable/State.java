package com.xfatur.validation.unique.executable;

public enum State {

    VALID(Boolean.TRUE), INVALID(Boolean.FALSE);

    private Boolean value;

    private State(Boolean value) {
	this.value = value;
    }

    public Boolean getValue() {
	return value;
    }
}
