package com.xfatur.validation.classconstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.validation.executable.State;

public class MultiClassificacaoFiscalValidator implements ConstraintValidator<ClassificacaoFiscalAnnotation, ClassificacaoFiscalDTO> {

    @Autowired
    private ClassificacaoFiscalService service;

    @Override
    public void initialize(ClassificacaoFiscalAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClassificacaoFiscalDTO dto, ConstraintValidatorContext context) {
	context.disableDefaultConstraintViolation();

	if (!(dto instanceof ClassificacaoFiscalDTO)) {
	    throw new IllegalArgumentException("@ClassificacaoFiscalAnnotation é aplicado em somente ClassificacaoFiscal");
	}

	Boolean hasDescricao = null;
	Boolean hasNcm = null;

	Integer id = dto.getId();
	String descricao = dto.getDescricao().trim();
	String ncm = dto.getNcm().trim();

	if (id == null) {
	    hasDescricao = service.hasDescricao(descricao);
	    hasNcm = service.hasNcm(ncm);
	} else {
	    hasDescricao = service.hasDescricao(descricao, id);
	    hasNcm = service.hasNcm(ncm, id);
	}

	if (hasDescricao) {
	    setMessage(context, "Descrição já cadastrada......", "descricao");
	}

	if (hasNcm) {
	    setMessage(context, "NCM já cadastrado.", "ncm");
	}

	if (hasNcm || hasDescricao) {
	    return State.INVALID.getValue();
	}
	return State.VALID.getValue();
    }

    private void setMessage(ConstraintValidatorContext context, String messageTemplate, String propertyName) {
	context.buildConstraintViolationWithTemplate(messageTemplate).addPropertyNode(propertyName).addConstraintViolation();
    }

}
