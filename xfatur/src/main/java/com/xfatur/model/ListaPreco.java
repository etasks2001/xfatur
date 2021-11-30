package com.xfatur.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ListaPreco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private LocalDate data;
    private String tipo;
    private String descricao;
    private BigDecimal descontoatacado;
    private BigDecimal descontovista;
    private BigDecimal desconto21dias;
    private BigDecimal desconto28dias;

    @OneToMany(mappedBy = "listaPreco", cascade = CascadeType.ALL)
    private Map<Integer, ListaPrecoItem> listaPrecoItem = new HashMap<Integer, ListaPrecoItem>();

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCodigo() {
	return codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public LocalDate getData() {
	return data;
    }

    public void setData(LocalDate data) {
	this.data = data;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public BigDecimal getDescontoatacado() {
	return descontoatacado;
    }

    public void setDescontoatacado(BigDecimal descontoatacado) {
	this.descontoatacado = descontoatacado;
    }

    public BigDecimal getDescontovista() {
	return descontovista;
    }

    public void setDescontovista(BigDecimal descontovista) {
	this.descontovista = descontovista;
    }

    public BigDecimal getDesconto21dias() {
	return desconto21dias;
    }

    public void setDesconto21dias(BigDecimal desconto21dias) {
	this.desconto21dias = desconto21dias;
    }

    public BigDecimal getDesconto28dias() {
	return desconto28dias;
    }

    public void setDesconto28dias(BigDecimal desconto28dias) {
	this.desconto28dias = desconto28dias;
    }

    public void addItem(ListaPrecoItem item) {
	listaPrecoItem.put(item.getProduto().getId(), item);
    }

}
