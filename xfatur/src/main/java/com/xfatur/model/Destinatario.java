package com.xfatur.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Destinatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CNPJCPF;
    private String idEstrangeiro;
    private String xNome;
    @Embedded
    private Endereco enderEmit;
    private String indIEDest;
    private String IE;
    private String ISUF;
    private String IM;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    private RamoAtividade ramoAtividade;
    @ManyToOne(fetch = FetchType.LAZY)
    private NaturezaJuridica naturezaJuridica;
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Endereco getEnderEmit() {
	return enderEmit;
    }

    public void setEnderEmit(Endereco enderEmit) {
	this.enderEmit = enderEmit;
    }

    public String getIndIEDest() {
	return indIEDest;
    }

    public void setIndIEDest(String indIEDest) {
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

}