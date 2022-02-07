package com.xfatur.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.validation.UniqueConstraint;
import com.xfatur.validation.constraints.Unique;
import com.xfatur.validation.factories.UniqueConstraintFactory;

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