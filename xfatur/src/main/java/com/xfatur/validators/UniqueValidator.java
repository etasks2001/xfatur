package com.xfatur.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

	@Autowired
	private UniqueConstraintFactory factory;

	private UniqueConstraint uniqueConstraint;

	@Override
	public void initialize(Unique unique) {
		uniqueConstraint = factory.get(unique.uniqueConstraint());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return uniqueConstraint.has(value);
	}

}