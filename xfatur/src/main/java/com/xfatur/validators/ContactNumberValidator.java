package com.xfatur.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.service.produto.ClassificacaoFiscalService;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

    @Autowired
    private ClassificacaoFiscalService service;

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
	System.out.println("size: " + contactField.length());

	Integer existsDescricao = service.existsDescricao(contactField.trim());

	System.out.println(contactField);
	System.out.println(existsDescricao);
	return contactField != null && existsDescricao == 0;
    }

}
