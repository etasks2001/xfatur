package com.xfatur.model.preco;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xfatur.model.produto.Produto;

@Entity
@Table(name = "ListaPrecoItem")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal precounitario;
    private String tipo;
    private Boolean destacar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listapreco_id")
    private Lista lista;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public BigDecimal getPrecounitario() {
	return precounitario;
    }

    public void setPrecounitario(BigDecimal precounitario) {
	this.precounitario = precounitario;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public Boolean getDestacar() {
	return destacar;
    }

    public void setDestacar(Boolean destacar) {
	this.destacar = destacar;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public void setLista(Lista lista) {
	this.lista = lista;
    }
}