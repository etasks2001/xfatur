package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.ProdutorService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.ProdutorDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class ProdutorChecker implements Checker {

    @Autowired
    private ProdutorService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	ProdutorDTO produtor = (ProdutorDTO) dto;
	Boolean hasDescricao = null;

	Integer id = produtor.getId();
	String descricao = produtor.getDescricao().trim();

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Produtor já cadastrado.", "descricao");
	    return State.INVALID.getValue();
	}

	return State.VALID.getValue();
    }
}