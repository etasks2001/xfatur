package com.xfatur.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.service.produto.ClassificacaoFiscalService;

public class ClassificacaoFiscalDescricao implements ConstraintValidator<Unique, String> {

	@Autowired
	private ClassificacaoFiscalService service;

	@Override
	public void initialize(Unique unique) {

	}

	@Override
	public boolean isValid(String descricao, ConstraintValidatorContext cxt) {

		Boolean hasDescricao = service.hasDescricao(descricao.trim());

		if (hasDescricao) {
			return Result.INVALID.getValue();
		}
		return Result.VALID.getValue();
	}
}
