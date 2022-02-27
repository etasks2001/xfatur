package com.xfatur.dto.produto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.TipoSeloChecker;

@Component
@MultiFielValidate(checker = TipoSeloChecker.class)
public class TipoSeloDTO implements DTO {

	private Integer id;

	@Pattern(regexp = "^[0-9][0-9][0-9][0-9][0-9][0-9].*", message = "Somente números")
	private String codigo;

	@Size(min = 3, max = 80)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
