package com.xfatur.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.ClassificacaoFiscalService;

@Component
public class DescricaoClassificacaoFiscalUniqueConstraint implements UniqueConstraint {

	@Autowired
	private ClassificacaoFiscalService service;

	@Override
	public boolean has(String descricao) {
		Boolean has = service.hasDescricao(descricao.trim());

		if (has) {
			return Result.INVALID.getValue();
		}
		return Result.VALID.getValue();
	}
}