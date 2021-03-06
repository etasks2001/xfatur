package com.xfatur.validation.multi.checkers;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;
import com.xfatur.validation.multi.MultiFieldValidator;
import com.xfatur.validation.unique.executable.State;

@Component
public class ClassificacaoFiscalChecker implements Checker {

    @Autowired
    private ClassificacaoFiscalService service;

    public boolean isValid(DTO dto, MultiFieldValidator validator, ConstraintValidatorContext context) {
	ClassificacaoFiscalDTO classificacaoFiscal = (ClassificacaoFiscalDTO) dto;

	Boolean hasDescricao = null;
	Boolean hasNcm = null;

	Integer id = classificacaoFiscal.getId();
	String descricao = classificacaoFiscal.getDescricao().trim();
	String ncm = classificacaoFiscal.getNcm().trim();

	hasDescricao = service.hasDescricao(id, descricao);
	hasNcm = service.hasNcm(id, ncm);

	if (hasDescricao) {
	    validator.setMessage(context, "Descrição já cadastrada.", "descricao");
	}

	if (hasNcm) {
	    validator.setMessage(context, "NCM já cadastrado.", "ncm");
	}

	if (hasNcm || hasDescricao) {
	    return State.INVALID.getValue();
	}
	return State.VALID.getValue();
    }
}