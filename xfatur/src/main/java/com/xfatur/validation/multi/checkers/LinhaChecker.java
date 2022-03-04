package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.LinhaService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.LinhaDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class LinhaChecker implements Checker {

    @Autowired
    private LinhaService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	LinhaDTO linha = (LinhaDTO) dto;
	Boolean hasDescricao = null;

	Integer id = linha.getId();
	String descricao = linha.getDescricao().trim();

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Linha já cadastrada.", "descricao");
	}

	if (hasDescricao) {
	    return State.INVALID.getValue();

	}

	return State.VALID.getValue();
    }
}