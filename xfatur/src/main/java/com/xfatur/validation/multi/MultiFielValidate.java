package com.xfatur.validation.multi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.xfatur.validation.multi.checkers.Checker;

@Constraint(validatedBy = MultiFieldValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiFielValidate {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends Checker> checker();
}
