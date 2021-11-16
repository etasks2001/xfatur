package com.xfatur.dto;

import com.xfatur.model.IndIEDest;

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
    private RamoAtividadeDTO ramoAtividadeDTO;
    private NaturezaJuridicaDTO naturezaJuridicaDTO;
    private RepresentanteDTO representanteDTO;
    private Integer ramoAtividade_id;
    private Integer naturezaJuridica_id;
    private Integer representante_id;
    private EntregaDTO entregaDTO;
    private RetiradaDTO retiradaDTO;

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

    public EnderecoDTO getEnderEmitDTO() {
	return enderEmitDTO;
    }

    public void setEnderEmitDTO(EnderecoDTO enderEmitDTO) {
	this.enderEmitDTO = enderEmitDTO;
    }

    public Integer getRamoAtividade_id() {
	return ramoAtividade_id;
    }

    public void setRamoAtividade_id(Integer ramoAtividade_id) {
	this.ramoAtividade_id = ramoAtividade_id;
    }

    public Integer getNaturezaJuridica_id() {
	return naturezaJuridica_id;
    }

    public void setNaturezaJuridica_id(Integer naturezaJuridica_id) {
	this.naturezaJuridica_id = naturezaJuridica_id;
    }

    public Integer getRepresentante_id() {
	return representante_id;
    }

    public void setRepresentante_id(Integer representante_id) {
	this.representante_id = representante_id;
    }

    public EntregaDTO getEntregaDTO() {
	return entregaDTO;
    }

    public void setEntregaDTO(EntregaDTO entregaDTO) {
	this.entregaDTO = entregaDTO;
    }

    public RetiradaDTO getRetiradaDTO() {
	return retiradaDTO;
    }

    public void setRetiradaDTO(RetiradaDTO retiradaDTO) {
	this.retiradaDTO = retiradaDTO;
    }

    public RamoAtividadeDTO getRamoAtividadeDTO() {
	return ramoAtividadeDTO;
    }

    public void setRamoAtividadeDTO(RamoAtividadeDTO ramoAtividadeDTO) {
	this.ramoAtividadeDTO = ramoAtividadeDTO;
    }

    public NaturezaJuridicaDTO getNaturezaJuridicaDTO() {
	return naturezaJuridicaDTO;
    }

    public void setNaturezaJuridicaDTO(NaturezaJuridicaDTO naturezaJuridicaDTO) {
	this.naturezaJuridicaDTO = naturezaJuridicaDTO;
    }

    public RepresentanteDTO getRepresentanteDTO() {
	return representanteDTO;
    }

    public void setRepresentanteDTO(RepresentanteDTO representanteDTO) {
	this.representanteDTO = representanteDTO;
    }

}
