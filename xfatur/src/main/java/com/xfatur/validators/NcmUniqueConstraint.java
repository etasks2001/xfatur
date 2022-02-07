package com.xfatur.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.ClassificacaoFiscalService;

@Component
public class NcmUniqueConstraint implements UniqueConstraint {

	@Autowired
	private ClassificacaoFiscalService service;

	@Override
	public boolean has(String ncm) {
		Boolean has = service.hasNcm(ncm.trim());

		if (has) {
			return Result.INVALID.getValue();
		}
		return Result.VALID.getValue();
	}
}