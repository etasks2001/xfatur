package com.xfatur.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueConstraintFactory {

	@Autowired
	private NcmUniqueConstraint uniqueConstraint;

	@Autowired
	private DescricaoClassificacaoFiscalUniqueConstraint descricaoClassificacaoFiscalUniqueConstraint;

	public UniqueConstraint get(Class<? extends UniqueConstraint> clazz) {
		if (clazz.equals(NcmUniqueConstraint.class)) {
			return uniqueConstraint;
		} else if (clazz.equals(DescricaoClassificacaoFiscalUniqueConstraint.class)) {
			return descricaoClassificacaoFiscalUniqueConstraint;
		}
		throw new IllegalStateException("Unique Constraint não localizado");
	}
}