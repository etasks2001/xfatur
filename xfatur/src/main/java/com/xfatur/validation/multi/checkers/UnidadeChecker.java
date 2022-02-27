package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.dto.produto.UnidadeDTO;
import com.xfatur.service.produto.UnidadeService;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class UnidadeChecker implements Checker {

	@Autowired
	private UnidadeService service;

	public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
		UnidadeDTO origem = (UnidadeDTO) dto;
		Boolean hasDescricao = null;
		Boolean hasAbreviacao = null;

		Integer id = origem.getId();
		String descricao = origem.getDescricao().trim();
		String abreviacao = origem.getAbreviacao().trim();

		hasDescricao = service.hasDescricao(id, descricao);

		if (hasDescricao) {
			validator.setMessage(context, "Unidade já cadastrada.", "descricao");
		}

		hasAbreviacao = service.hasAbreviacao(id, abreviacao);
		if (hasAbreviacao) {
			validator.setMessage(context, "Abreviação já cadastrada.", "abreviacao");
		}
		if (hasAbreviacao || hasDescricao) {
			return State.INVALID.getValue();

		}

		return State.VALID.getValue();
	}
}