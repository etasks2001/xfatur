package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.dto.produto.TipoDTO;
import com.xfatur.service.produto.TipoService;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class TipoChecker implements Checker {

    @Autowired
    private TipoService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	TipoDTO cf = (TipoDTO) dto;
	Boolean hasDescricao = null;
	Boolean hasCodigo = null;

	Integer id = cf.getId();
	String descricao = cf.getDescricao().trim();
	String codigo = cf.getCodigo().trim();

	hasDescricao = service.hasDescricao(id, descricao);
	hasCodigo = service.hasCodigo(id, codigo);

	if (hasDescricao) {
	    validator.setMessage(context, "Descrição já cadastrada.", "descricao");
	}

	if (hasCodigo) {
	    validator.setMessage(context, "NCM já cadastrado.", "codigo");
	}

	if (hasCodigo || hasDescricao) {
	    return State.INVALID.getValue();
	}
	return State.VALID.getValue();
    }
}