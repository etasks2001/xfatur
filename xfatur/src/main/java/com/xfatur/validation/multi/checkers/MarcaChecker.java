package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.dto.produto.MarcaDTO;
import com.xfatur.service.produto.MarcaService;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class MarcaChecker implements Checker {

	@Autowired
	private MarcaService service;

	public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
		MarcaDTO marca = (MarcaDTO) dto;
		Boolean hasDescricao = null;

		Integer id = marca.getId();
		String descricao = marca.getDescricao().trim();

		hasDescricao = service.hasDescricao(id, descricao);

		if (hasDescricao) {
			validator.setMessage(context, "Marca já cadastrada.", "descricao");
			return State.INVALID.getValue();
		}

		return State.VALID.getValue();
	}
}