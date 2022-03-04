package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.TipoSeloService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.TipoSeloDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class TipoSeloChecker implements Checker {

    @Autowired
    private TipoSeloService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	TipoSeloDTO tiposelo = (TipoSeloDTO) dto;
	Boolean hasDescricao = null;
	Boolean hasCodigo = null;

	Integer id = tiposelo.getId();
	String codigo = tiposelo.getCodigo();
	String descricao = tiposelo.getDescricao().trim();

	hasCodigo = service.hasCodigo(id, codigo);
	if (hasCodigo) {
	    validator.setMessage(context, "Código já cadastrado.", "codigo");
	}

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Tipo de Selo já cadastrado.", "descricao");
	}

	if (hasCodigo || hasDescricao) {
	    return State.INVALID.getValue();

	}

	return State.VALID.getValue();
    }
}