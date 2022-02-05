package com.xfatur.validators;

public enum Result {

	VALID(Boolean.TRUE), INVALID(Boolean.FALSE);

	private Boolean value;

	private Result(Boolean value) {
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}
}
