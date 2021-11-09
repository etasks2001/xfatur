package com.xfatur.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfatur.dto.EmitDTO;
import com.xfatur.dto.EnderDTO;

@Entity
@Table(name = "Emit")
public class Emit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CNPJ;
    private String xNome;
    private String xFant;
    @Embedded
    private Ender enderEmit;
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

    public Ender getEnderEmit() {
	return enderEmit;
    }

    public void setEnderEmit(Ender enderEmit) {
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

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public static Emit convert(EmitDTO emitDTO) {
	Emit emit = new Emit();

	emit.setId(emitDTO.getId());
	emit.setCNPJ(emitDTO.getCNPJ());
	emit.setxNome(emitDTO.getxNome());
	emit.setxFant(emitDTO.getxFant());

	emit.setEnderEmit(convert(emitDTO.getEnderEmitDTO()));
	emit.setIE(emitDTO.getIE());
	emit.setIEST(emitDTO.getIEST());
	emit.setIM(emitDTO.getIM());
	emit.setCNAE(emitDTO.getCNAE());
	emit.setCRT(emitDTO.getCRT());
	emit.setNf_serie_atual(emitDTO.getNf_serie_atual());
	emit.setUltima_nnf(emitDTO.getUltima_nnf());

	return emit;

    }

    public static Ender convert(EnderDTO enderDTO) {

	Ender ender = new Ender();

	ender.setxLgr(enderDTO.getxLgr());
	ender.setNro(enderDTO.getNro());
	ender.setxCpl(enderDTO.getxCpl());
	ender.setxBairro(enderDTO.getxBairro());
	ender.setcMun(enderDTO.getcMun());
	ender.setxMun(enderDTO.getxMun());
	ender.setUF(enderDTO.getUF());
	ender.setCEP(enderDTO.getCEP());
	ender.setcPais(enderDTO.getcPais());
	ender.setxPais(enderDTO.getxPais());
	ender.setFone(enderDTO.getFone());

	return ender;
    }

}
