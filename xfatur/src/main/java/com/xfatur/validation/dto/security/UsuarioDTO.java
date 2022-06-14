package com.xfatur.validation.dto.security;

import java.util.ArrayList;
import java.util.List;

import com.xfatur.model.security.PerfilTipo;

public class UsuarioDTO {
    private Integer id;

    private String email;

    private String senha;

    private List<PerfilDTO> perfisDTO = new ArrayList<PerfilDTO>();

    private boolean ativo;

    public UsuarioDTO() {
	super();
    }

    public void addPerfil(PerfilTipo tipo) {
	if (this.perfisDTO == null) {
	    this.perfisDTO = new ArrayList<>();
	}
	this.perfisDTO.add(new PerfilDTO(tipo.getCod()));
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public List<PerfilDTO> getPerfisDTO() {
	return perfisDTO;
    }

    public void setPerfisDTO(List<PerfilDTO> perfisDTO) {
	this.perfisDTO = perfisDTO;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

}
