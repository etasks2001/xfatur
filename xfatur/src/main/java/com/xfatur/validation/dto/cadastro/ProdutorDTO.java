package com.xfatur.validation.dto.cadastro;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.validation.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.ProdutorChecker;

@Component
@MultiFielValidate(checker = ProdutorChecker.class)
public class ProdutorDTO implements DTO {

    private Integer id;

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

}
