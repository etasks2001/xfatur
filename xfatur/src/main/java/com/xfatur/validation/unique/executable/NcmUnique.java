package com.xfatur.validation.unique.executable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.validation.unique.UniqueConstraint;

@Component
public class NcmUnique implements UniqueConstraint {

	@Autowired
	private ClassificacaoFiscalService service;

	@Override
	public boolean has(String ncm) {
//	Boolean has = service.hasNcm(ncm.trim());
//
//		if (has) {
//			return State.INVALID.getValue();
//		}
		return State.VALID.getValue();
	}
}