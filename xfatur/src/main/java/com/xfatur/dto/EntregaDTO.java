package com.xfatur.dto;

public class EntregaDTO {

	private Integer id;

	private LocalDTO localDTO;

	private DestinatarioDTO destinatarioDTO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDTO getLocalDTO() {
		return localDTO;
	}

	public void setLocalDTO(LocalDTO localDTO) {
		this.localDTO = localDTO;
	}

	public DestinatarioDTO getDestinatarioDTO() {
		return destinatarioDTO;
	}

	public void setDestinatarioDTO(DestinatarioDTO destinatarioDTO) {
		this.destinatarioDTO = destinatarioDTO;
	}

}
