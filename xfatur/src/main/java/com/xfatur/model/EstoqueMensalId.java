package com.xfatur.model;

import java.io.Serializable;

import com.xfatur.model.produto.Produto;

public class EstoqueMensalId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer mes;
    private Integer ano;
    private Produto produto;

    public Integer getMes() {
	return mes;
    }

    public void setMes(Integer mes) {
	this.mes = mes;
    }

    public Integer getAno() {
	return ano;
    }

    public void setAno(Integer ano) {
	this.ano = ano;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

}
