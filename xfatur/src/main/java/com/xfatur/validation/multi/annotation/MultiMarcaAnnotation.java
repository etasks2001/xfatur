package com.xfatur.validation.multi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.validator.MultiMarcaValidator;

@Constraint(validatedBy = MultiMarcaValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiMarcaAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends DTO> dto();
}
