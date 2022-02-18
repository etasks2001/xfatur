package com.xfatur.validation.multi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.dto.produto.MarcaDTO;
import com.xfatur.service.produto.MarcaService;
import com.xfatur.validation.executable.State;
import com.xfatur.validation.multi.annotation.MultiMarcaAnnotation;

public class MultiMarcaValidator implements ConstraintValidator<MultiMarcaAnnotation, MarcaDTO> {

    @Autowired
    private MarcaService service;

    @Override
    public void initialize(MultiMarcaAnnotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(MarcaDTO dto, ConstraintValidatorContext context) {
	context.disableDefaultConstraintViolation();

	if (!(dto instanceof MarcaDTO)) {
	    throw new IllegalArgumentException("@ClassificacaoFiscalAnnotation é aplicado em somente ClassificacaoFiscal");
	}

	Boolean hasDescricao = null;

	Integer id = dto.getId();
	String descricao = dto.getDescricao().trim();

	if (id == null) {
	    hasDescricao = service.hasDescricao(descricao);
	} else {
	    hasDescricao = service.hasDescricao(id, descricao);
	}

	if (hasDescricao) {
	    setMessage(context, "Marca já cadastrada.", "descricao");
	}

	if (hasDescricao) {
	    return State.INVALID.getValue();
	}
	return State.VALID.getValue();
    }

    private void setMessage(ConstraintValidatorContext context, String messageTemplate, String propertyName) {
	context.buildConstraintViolationWithTemplate(messageTemplate).addPropertyNode(propertyName).addConstraintViolation();
    }
}