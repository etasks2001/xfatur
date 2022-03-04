package com.xfatur.validation.dto.cadastro;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.ClassificacaoFiscalChecker;

@Component
@MultiFielValidate(checker = ClassificacaoFiscalChecker.class)
public class ClassificacaoFiscalDTO implements DTO {

	private Integer id;

	@Pattern(regexp = "^\\d{4}[\\.]\\d{4}$", message = "Apenas números")
	private String ncm;

	@Size(min = 3, max = 80)
	private String descricao;

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

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

}