package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.MultiFieldValidator;

public interface Checker {

	public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context);
}
