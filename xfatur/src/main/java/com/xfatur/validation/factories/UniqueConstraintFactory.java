package com.xfatur.validation.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.xfatur.validation.UniqueConstraint;

@Component
public class UniqueConstraintFactory {

    @Autowired
    private ApplicationContext context;

    public UniqueConstraint get(Class<? extends UniqueConstraint> clazz) {

	UniqueConstraint bean = context.getBean(clazz);

	return bean;
    }
}