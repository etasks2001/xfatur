package com.xfatur.validation.unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private BeanFactory beanFactory;

    private UniqueConstraint uniqueConstraint;

    @Override
    public void initialize(Unique unique) {
	uniqueConstraint = beanFactory.getBean(unique.uniqueConstraint());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

	return uniqueConstraint.has(value);
    }
}