package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.dto.produto.RegiaoProdutoraDTO;
import com.xfatur.service.produto.RegiaoProdutoraService;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class RegiaoProdutoraChecker implements Checker {

    @Autowired
    private RegiaoProdutoraService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	RegiaoProdutoraDTO regiaoProdutora = (RegiaoProdutoraDTO) dto;
	Boolean hasDescricao = null;

	Integer id = regiaoProdutora.getId();
	String descricao = regiaoProdutora.getDescricao().trim();

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Regiao Produtora já cadastrada.", "descricao");
	    return State.INVALID.getValue();
	}

	return State.VALID.getValue();
    }
}