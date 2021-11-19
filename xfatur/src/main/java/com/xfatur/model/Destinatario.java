package com.xfatur.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.xfatur.model.test.EnderecoCobranca;

@Entity
public class Destinatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CNPJCPF;
    private String idEstrangeiro;
    private String xNome;
    @Embedded
    private Endereco enderDest;
    private IndIEDest indIEDest;
    private String IE;
    private String ISUF;
    private String IM;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "destinatario", fetch = FetchType.LAZY)
    private EnderecoEntrega enderecoEntrega;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "destinatario", fetch = FetchType.LAZY)
    private EnderecoRetirada enderecoRetirada;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "destinatario", fetch = FetchType.LAZY)
    private EnderecoCobranca enderecoCobranca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ramoatividade_id")
    private RamoAtividade ramoAtividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "naturezajuridica_id")
    private NaturezaJuridica naturezaJuridica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "representante_id")
    private Representante representante;

    public Integer getId() {
	return id;
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

    public Endereco getEnderDest() {
	return enderDest;
    }

    public void setEnderDest(Endereco enderDest) {
	this.enderDest = enderDest;
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

    public EnderecoEntrega getEnderecoEntrega() {
	return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
	this.enderecoEntrega = enderecoEntrega;
    }

    public EnderecoRetirada getEnderecoRetirada() {
	return enderecoRetirada;
    }

    public void setEnderecoRetirada(EnderecoRetirada enderecoRetirada) {
	this.enderecoRetirada = enderecoRetirada;
    }

    public EnderecoCobranca getEnderecoCobranca() {
	return enderecoCobranca;
    }

    public void setEnderecoCobranca(EnderecoCobranca enderecoCobranca) {
	this.enderecoCobranca = enderecoCobranca;
    }

}