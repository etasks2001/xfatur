package com.xfatur.dto;

import java.util.List;

public class NaturezaJuridicaDTO {

    private Integer id;
    private String descricao;
    private List<DestinatarioDTO> destinatarioDTO;

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

    public List<DestinatarioDTO> getDestinatarioDTO() {
	return destinatarioDTO;
    }

    public void setDestinatarioDTO(List<DestinatarioDTO> destinatarioDTO) {
	this.destinatarioDTO = destinatarioDTO;
    }

}
