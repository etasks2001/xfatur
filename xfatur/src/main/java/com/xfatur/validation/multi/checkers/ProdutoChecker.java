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
		Boolean hasCodigoProduto = null;

		Integer id = produtor.getId();
		String codigoProduto = produtor.getDescricao().trim();

		hasCodigoProduto = service.hasCodigoProduto(id, codigoProduto);

		if (hasCodigoProduto) {
			validator.setMessage(context, "Código do Produto já cadastrado.", "codigoProduto");
			return State.INVALID.getValue();
		}

		return State.VALID.getValue();
	}
}