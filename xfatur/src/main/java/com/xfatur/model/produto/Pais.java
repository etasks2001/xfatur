package com.xfatur.model.produto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sigla;
    private String origem;
    private String codigoBacen;

    @OneToMany(mappedBy = "pais")
    private List<Produto> produtos;

    public Integer getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSigla() {
	return sigla;
    }

    public void setSigla(String sigla) {
	this.sigla = sigla;
    }

    public String getOrigem() {
	return origem;
    }

    public void setOrigem(String origem) {
	this.origem = origem;
    }

    public String getCodigoBacen() {
	return codigoBacen;
    }

    public void setCodigoBacen(String codigoBacen) {
	this.codigoBacen = codigoBacen;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
