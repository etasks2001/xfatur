package com.xfatur.model.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String abreviacao;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Integer getId() {
	return id;
    }

    public String getAbreviacao() {
	return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
	this.abreviacao = abreviacao;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
