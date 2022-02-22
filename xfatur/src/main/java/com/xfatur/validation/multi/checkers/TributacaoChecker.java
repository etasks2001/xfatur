package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.dto.produto.TributacaoDTO;
import com.xfatur.service.produto.TributacaoService;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class TributacaoChecker implements Checker {

    @Autowired
    private TributacaoService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	TributacaoDTO tributacao = (TributacaoDTO) dto;
	Boolean hasDescricao = null;
	Boolean hasCodigo = null;

	Integer id = tributacao.getId();
	String codigo = tributacao.getCodigo();
	String descricao = tributacao.getDescricao().trim();

	hasCodigo = service.hasCodigo(id, codigo);
	if (hasCodigo) {
	    validator.setMessage(context, "Código já cadastrado.", "codigo");
	}

	hasDescricao = service.hasDescricao(id, descricao);

	if (hasDescricao) {
	    validator.setMessage(context, "Tributacao já cadastrada.", "descricao");
	}

	if (hasCodigo || hasDescricao) {
	    return State.INVALID.getValue();

	}

	return State.VALID.getValue();
    }
}