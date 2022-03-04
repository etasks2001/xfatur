package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.PaisService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.PaisDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class PaisChecker implements Checker {

	@Autowired
	private PaisService service;

	public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
		PaisDTO pais = (PaisDTO) dto;
		Boolean hasNome = null;
		Boolean hasSigla = null;
		Boolean hasOrigem = null;
		Boolean hasCodigoBacen = null;

		Integer id = pais.getId();
		String nome = pais.getNome().trim();
		String sigla = pais.getSigla().trim();
		String origem = pais.getOrigem().trim();
		String codigoBacen = pais.getCodigoBacen();

		hasNome = service.hasNome(id, nome);
		if (hasNome) {
			validator.setMessage(context, "País já cadastrado.", "nome");
		}

		hasSigla = service.hasSigla(id, sigla);
		if (hasSigla) {
			validator.setMessage(context, "Sigla já cadastrada.", "sigla");
		}

		hasOrigem = service.hasOrigem(id, origem);
		if (hasOrigem) {
			validator.setMessage(context, "Origem já cadastrada.", "origem");
		}

		hasCodigoBacen = service.hasCodigoBacen(id, codigoBacen);
		if (hasCodigoBacen) {
			validator.setMessage(context, "Código Bacen já cadastrado.", "codigoBacen");
		}

		if (hasNome || hasSigla || hasOrigem || hasCodigoBacen) {
			return State.INVALID.getValue();

		}

		return State.VALID.getValue();
	}
}