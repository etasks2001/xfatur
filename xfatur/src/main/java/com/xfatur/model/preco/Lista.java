package com.xfatur.model.preco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ListaPreco")
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    @Column(insertable = false, updatable = false)
    private LocalDate data;
    private String tipo;
    private String descricao;
    private BigDecimal descontoAtacado;
    private BigDecimal descontoVista;
    private BigDecimal desconto21Dias;
    private BigDecimal desconto28Dias;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<Item>();

    public Integer getId() {
	return id;
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

    public BigDecimal getDescontoAtacado() {
	return descontoAtacado;
    }

    public void setDescontoAtacado(BigDecimal descontoAtacado) {
	this.descontoAtacado = descontoAtacado;
    }

    public BigDecimal getDescontoVista() {
	return descontoVista;
    }

    public void setDescontoVista(BigDecimal descontoVista) {
	this.descontoVista = descontoVista;
    }

    public BigDecimal getDesconto21Dias() {
	return desconto21Dias;
    }

    public void setDesconto21Dias(BigDecimal desconto21Dias) {
	this.desconto21Dias = desconto21Dias;
    }

    public BigDecimal getDesconto28Dias() {
	return desconto28Dias;
    }

    public void setDesconto28Dias(BigDecimal desconto28Dias) {
	this.desconto28Dias = desconto28Dias;
    }

    public void addItem(Item listaPrecoItem) {
	listaPrecoItem.setLista(this);
	itens.add(listaPrecoItem);
    }

    public List<Item> getItens() {
	return itens;
    }

    public void delete(int index) {
	itens.remove(index);

    }
}
