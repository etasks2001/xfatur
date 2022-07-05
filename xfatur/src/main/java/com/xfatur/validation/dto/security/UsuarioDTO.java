package com.xfatur.validation.dto.security;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    private Integer id;

    private String email;

    private String senha;

    private String codigoVerificador;

    private List<PerfilDTO> perfisDTO = new ArrayList<PerfilDTO>();

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

    public String getCodigoVerificador() {
	return codigoVerificador;
    }

    public void setCodigoVerificador(String codigoVerificador) {
	this.codigoVerificador = codigoVerificador;
    }

}
