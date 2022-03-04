package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.TipoItemService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.TipoItemDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class TipoItemChecker implements Checker {

    @Autowired
    private TipoItemService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	TipoItemDTO tipoItem = (TipoItemDTO) dto;
	Boolean hasDescricao = null;
	Boolean hasCodigo = null;

	Integer id = tipoItem.getId();
	String descricao = tipoItem.getDescricao().trim();
	String codigo = tipoItem.getCodigo().trim();

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