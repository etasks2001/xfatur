package com.xfatur.dto.produto;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.UnidadeChecker;

@Component
@MultiFielValidate(checker = UnidadeChecker.class)
public class UnidadeDTO implements DTO {

    private Integer id;

    @Size(min = 3, max = 80)
    private String descricao;

    @Size(min = 1, max = 6)
    private String abreviacao;

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

    public String getAbreviacao() {
	return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
	this.abreviacao = abreviacao;
    }

}
