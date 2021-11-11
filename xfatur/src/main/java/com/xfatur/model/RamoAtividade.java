package com.xfatur.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RamoAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public List<Destinatario> getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(List<Destinatario> destinatario) {
	this.destinatario = destinatario;
    }

    private String descricao;
    @OneToMany(mappedBy = "ramoAtividade")
    private List<Destinatario> destinatario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

}