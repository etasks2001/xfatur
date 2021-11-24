package com.xfatur.model.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tipo {
    @Id
    private String id;
    private String descricao;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

}
