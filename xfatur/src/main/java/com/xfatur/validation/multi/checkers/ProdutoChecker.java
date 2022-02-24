package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.dto.produto.ProdutoDTO;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class ProdutoChecker implements Checker {

    @Autowired
    private ProdutoService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	ProdutoDTO produtor = (ProdutoDTO) dto;
	Boolean hasDescricao = null;

	Integer id = produtor.getId();
	String descricao = produtor.getDescricao().trim();

//	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Produto já cadastrado.", "descricao");
	    return State.INVALID.getValue();
	}

	return State.VALID.getValue();
    }
}