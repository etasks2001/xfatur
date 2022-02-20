package com.xfatur.validation.multi;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.checkers.Checker;

public class MultiFieldValidator implements ConstraintValidator<MultiFielValidate, DTO> {

	@Autowired
	private BeanFactory factory;

	private MultiFielValidate annotation;

	@Override
	public void initialize(MultiFielValidate annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(DTO dto, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		Checker checker = factory.getBean(annotation.checker());

		return checker.isValid(dto, this, context);
	}

	public void setMessage(ConstraintValidatorContext context, String messageTemplate, String propertyName) {
		context.buildConstraintViolationWithTemplate(messageTemplate).addPropertyNode(propertyName).addConstraintViolation();
	}
}