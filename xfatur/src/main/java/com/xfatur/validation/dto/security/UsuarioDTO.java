package com.xfatur.validation.dto.security;

import java.util.List;

public class UsuarioDTO {
    private Integer id;

    private String email;

    private String senha;

    private List<PerfilDTO> perfis;

    private boolean ativo;

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

    public List<PerfilDTO> getPerfis() {
	return perfis;
    }

    public void setPerfis(List<PerfilDTO> perfis) {
	this.perfis = perfis;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

}
