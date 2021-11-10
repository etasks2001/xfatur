package com.xfatur.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.xfatur.dto.EmitenteDTO;
import com.xfatur.dto.EnderecoDTO;

@Entity
public class Emitente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CNPJ;
    private String xNome;
    private String xFant;
    @Embedded
    private Endereco enderEmit;
    private String IE;
    private String IEST;
    private String IM;
    private String CNAE;
    private String CRT;
    private Integer nf_serie_atual;
    private Integer ultima_nnf;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCNPJ() {
	return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
	CNPJ = cNPJ;
    }

    public String getxNome() {
	return xNome;
    }

    public void setxNome(String xNome) {
	this.xNome = xNome;
    }

    public String getxFant() {
	return xFant;
    }

    public void setxFant(String xFant) {
	this.xFant = xFant;
    }

    public Endereco getEnderEmit() {
	return enderEmit;
    }

    public void setEnderEmit(Endereco enderEmit) {
	this.enderEmit = enderEmit;
    }

    public String getIE() {
	return IE;
    }

    public void setIE(String iE) {
	IE = iE;
    }

    public String getIEST() {
	return IEST;
    }

    public void setIEST(String iEST) {
	IEST = iEST;
    }

    public String getIM() {
	return IM;
    }

    public void setIM(String iM) {
	IM = iM;
    }

    public String getCNAE() {
	return CNAE;
    }

    public void setCNAE(String cNAE) {
	CNAE = cNAE;
    }

    public String getCRT() {
	return CRT;
    }

    public void setCRT(String cRT) {
	CRT = cRT;
    }

    public Integer getNf_serie_atual() {
	return nf_serie_atual;
    }

    public void setNf_serie_atual(Integer nf_serie_atual) {
	this.nf_serie_atual = nf_serie_atual;
    }

    public Integer getUltima_nnf() {
	return ultima_nnf;
    }

    public void setUltima_nnf(Integer ultima_nnf) {
	this.ultima_nnf = ultima_nnf;
    }

    public static Emitente convert(EmitenteDTO emitenteDTO) {
	Emitente emitente = new Emitente();

	emitente.setId(emitenteDTO.getId());
	emitente.setCNPJ(emitenteDTO.getCNPJ());
	emitente.setxNome(emitenteDTO.getxNome());
	emitente.setxFant(emitenteDTO.getxFant());

	emitente.setEnderEmit(convert(emitenteDTO.getEnderEmitDTO()));
	emitente.setIE(emitenteDTO.getIE());
	emitente.setIEST(emitenteDTO.getIEST());
	emitente.setIM(emitenteDTO.getIM());
	emitente.setCNAE(emitenteDTO.getCNAE());
	emitente.setCRT(emitenteDTO.getCRT());
	emitente.setNf_serie_atual(emitenteDTO.getNf_serie_atual());
	emitente.setUltima_nnf(emitenteDTO.getUltima_nnf());

	return emitente;

    }

    public static Endereco convert(EnderecoDTO enderecoDTO) {

	Endereco endereco = new Endereco();

	endereco.setxLgr(enderecoDTO.getxLgr());
	endereco.setNro(enderecoDTO.getNro());
	endereco.setxCpl(enderecoDTO.getxCpl());
	endereco.setxBairro(enderecoDTO.getxBairro());
	endereco.setcMun(enderecoDTO.getcMun());
	endereco.setxMun(enderecoDTO.getxMun());
	endereco.setUF(enderecoDTO.getUF());
	endereco.setCEP(enderecoDTO.getCEP());
	endereco.setcPais(enderecoDTO.getcPais());
	endereco.setxPais(enderecoDTO.getxPais());
	endereco.setFone(enderecoDTO.getFone());

	return endereco;
    }

}
