package com.xfatur.dto;

import com.xfatur.model.IndIEDest;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;

public class DestinatarioDTO {
    private Integer id;
    private String CNPJCPF;
    private String idEstrangeiro;
    private String xNome;
    private EnderecoDTO enderEmitDTO;
    private IndIEDest indIEDest;
    private String IE;
    private String ISUF;
    private String IM;
    private String email;
    private RamoAtividade ramoAtividade;
    private NaturezaJuridica naturezaJuridica;
    private Representante representante;

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

    public String getIdEstrangeiro() {
	return idEstrangeiro;
    }

    public void setIdEstrangeiro(String idEstrangeiro) {
	this.idEstrangeiro = idEstrangeiro;
    }

    public String getxNome() {
	return xNome;
    }

    public void setxNome(String xNome) {
	this.xNome = xNome;
    }

    public IndIEDest getIndIEDest() {
	return indIEDest;
    }

    public void setIndIEDest(IndIEDest indIEDest) {
	this.indIEDest = indIEDest;
    }

    public String getIE() {
	return IE;
    }

    public void setIE(String iE) {
	IE = iE;
    }

    public String getISUF() {
	return ISUF;
    }

    public void setISUF(String iSUF) {
	ISUF = iSUF;
    }

    public String getIM() {
	return IM;
    }

    public void setIM(String iM) {
	IM = iM;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public RamoAtividade getRamoAtividade() {
	return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
	this.ramoAtividade = ramoAtividade;
    }

    public NaturezaJuridica getNaturezaJuridica() {
	return naturezaJuridica;
    }

    public void setNaturezaJuridica(NaturezaJuridica naturezaJuridica) {
	this.naturezaJuridica = naturezaJuridica;
    }

    public Representante getRepresentante() {
	return representante;
    }

    public void setRepresentante(Representante representante) {
	this.representante = representante;
    }

    public EnderecoDTO getEnderEmitDTO() {
	return enderEmitDTO;
    }

    public void setEnderEmitDTO(EnderecoDTO enderEmitDTO) {
	this.enderEmitDTO = enderEmitDTO;
    }

}
