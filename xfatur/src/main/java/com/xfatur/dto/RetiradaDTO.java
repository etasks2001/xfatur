package com.xfatur.dto;

public class RetiradaDTO {

    private Integer id;

    private LocalDTO local;

    private DestinatarioDTO destinatarioDTO;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public LocalDTO getLocal() {
	return local;
    }

    public void setLocal(LocalDTO local) {
	this.local = local;
    }

    public DestinatarioDTO getDestinatarioDTO() {
	return destinatarioDTO;
    }

    public void setDestinatarioDTO(DestinatarioDTO destinatarioDTO) {
	this.destinatarioDTO = destinatarioDTO;
    }

}
