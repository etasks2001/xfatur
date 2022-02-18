package com.xfatur.dto.produto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.validation.classconstraint.ClassificacaoFiscalAnnotation;

@Component
@ClassificacaoFiscalAnnotation
public class ClassificacaoFiscalDTO {

    private Integer id;

//	@Unique(uniqueConstraint = NcmUnique.class, message = "NCM já está cadastrado.")
    @Pattern(regexp = "^\\d{4}[\\.]\\d{4}$", message = "Deve corresponder a 9999.9999")
    private String ncm;

//	@Unique(uniqueConstraint = DescricaoClassificacaoFiscalUnique.class, message = "Descrição já está cadastrada.")
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

    @Override
    public String toString() {
	return "ClassificacaoFiscalDTO [id=" + id + ", ncm=" + ncm + ", descricao=" + descricao + "]";
    }

}