package com.xfatur.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.xfatur.model.produto.Produto;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "unique_produto", columnNames = { "mes", "ano", "produto_id" }) })
public class EstoqueMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mes;
    private Integer ano;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidadeInicial;
    private BigDecimal custoUnitario;

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

    public Integer getQuantidadeInicial() {
	return quantidadeInicial;
    }

    public void setQuantidadeInicial(Integer quantidadeInicial) {
	this.quantidadeInicial = quantidadeInicial;
    }

    public BigDecimal getCustoUnitario() {
	return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario) {
	this.custoUnitario = custoUnitario;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public Integer getId() {
	return id;
    }

}
