package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.OrigemService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.OrigemDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class OrigemChecker implements Checker {

    @Autowired
    private OrigemService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	OrigemDTO origem = (OrigemDTO) dto;
	Boolean hasDescricao = null;
	Boolean hasCodigo = null;

	Integer id = origem.getId();
	String codigo = origem.getCodigo();
	String descricao = origem.getDescricao().trim();

	hasCodigo = service.hasCodigo(id, codigo);
	if (hasCodigo) {
	    validator.setMessage(context, "Código já cadastrado.", "codigo");
	}

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Origem já cadastrada.", "descricao");
	}

	if (hasCodigo || hasDescricao) {
	    return State.INVALID.getValue();

	}

	return State.VALID.getValue();
    }
}