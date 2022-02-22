package com.xfatur.dto.produto;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.TipoValidadeChecker;

@Component
@MultiFielValidate(checker = TipoValidadeChecker.class)
public class TipoValidadeDTO implements DTO {

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
