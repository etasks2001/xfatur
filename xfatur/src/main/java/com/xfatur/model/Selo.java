package com.xfatur.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.xfatur.model.produto.Produto;

@Entity
public class Selo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroGuia;
    private LocalDate dataGuia;
    private String numeroInicial;
    private String numeroFinal;
    private Boolean substituicao;
    private String observacao;
    private String codigoSelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNumeroGuia() {
	return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
	this.numeroGuia = numeroGuia;
    }

    public LocalDate getDataGuia() {
	return dataGuia;
    }

    public void setDataGuia(LocalDate dataGuia) {
	this.dataGuia = dataGuia;
    }

    public String getNumeroInicial() {
	return numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
	this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
	return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
	this.numeroFinal = numeroFinal;
    }

    public Boolean getSubstituicao() {
	return substituicao;
    }

    public void setSubstituicao(Boolean substituicao) {
	this.substituicao = substituicao;
    }

    public String getObservacao() {
	return observacao;
    }

    public void setObservacao(String observacao) {
	this.observacao = observacao;
    }

    public String getCodigoSelo() {
	return codigoSelo;
    }

    public void setCodigoSelo(String codigoSelo) {
	this.codigoSelo = codigoSelo;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

}
