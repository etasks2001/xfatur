package com.xfatur.validation.dto.cadastro;

import javax.validation.constraints.Size;

import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.LinhaChecker;

@MultiFielValidate(checker = LinhaChecker.class)
public class LinhaDTO implements DTO {

    private Integer id;

    @Size(min = 3, max = 80)
    private String descricao;

    private String tipo;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

}
