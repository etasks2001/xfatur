package com.xfatur.model.produto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Linha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String tipo;

    private Integer ordem = 0;

    @OneToMany(mappedBy = "linha")
    List<Produto> produtos;

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public Integer getOrdem() {
	return ordem;
    }

    public void setOrdem(Integer ordem) {
	this.ordem = ordem;
    }

    public Integer getId() {
	return id;
    }

}
