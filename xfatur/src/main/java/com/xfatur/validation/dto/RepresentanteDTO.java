package com.xfatur.validation.dto;

import java.util.List;

import com.xfatur.model.Endereco;

public class RepresentanteDTO {
    private Integer id;
    private String CNPJCPF;
    private String xNome;
    private Endereco endereco;
    private String IE;
    private String email;
    private List<DestinatarioDTO> destinatarioDTO;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCNPJCPF() {
	return CNPJCPF;
    }

    public void setCNPJCPF(String cNPJCPF) {
	CNPJCPF = cNPJCPF;
    }

    public String getxNome() {
	return xNome;
    }

    public void setxNome(String xNome) {
	this.xNome = xNome;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public String getIE() {
	return IE;
    }

    public void setIE(String iE) {
	IE = iE;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public List<DestinatarioDTO> getDestinatarioDTO() {
	return destinatarioDTO;
    }

    public void setDestinatarioDTO(List<DestinatarioDTO> destinatarioDTO) {
	this.destinatarioDTO = destinatarioDTO;
    }

}
