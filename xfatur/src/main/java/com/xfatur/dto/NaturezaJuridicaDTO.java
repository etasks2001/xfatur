package com.xfatur.dto;

import java.util.List;

import com.xfatur.model.Destinatario;

public class NaturezaJuridicaDTO {

    private Integer id;
    private String descricao;
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

    public List<Destinatario> getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(List<Destinatario> destinatario) {
	this.destinatario = destinatario;
    }

}
