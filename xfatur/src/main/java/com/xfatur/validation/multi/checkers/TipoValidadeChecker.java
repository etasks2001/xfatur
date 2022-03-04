package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.TipoValidadeService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.TipoValidadeDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class TipoValidadeChecker implements Checker {

    @Autowired
    private TipoValidadeService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	TipoValidadeDTO tipoValidade = (TipoValidadeDTO) dto;
	Boolean hasDescricao = null;

	Integer id = tipoValidade.getId();
	String descricao = tipoValidade.getDescricao().trim();

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Tipo Validade já cadastrada.", "descricao");
	    return State.INVALID.getValue();
	}

	return State.VALID.getValue();
    }

}