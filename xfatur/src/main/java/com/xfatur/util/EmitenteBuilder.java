package com.xfatur.util;

import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;

public class EmitenteBuilder {

    private Emitente emit;

    private EmitenteBuilder() {
	this.emit = new Emitente();
    }

    public static EmitenteBuilder builder() {
	return new EmitenteBuilder();
    }

    public EmitenteBuilder addId(Integer id) {
	this.emit.setId(id);
	return this;
    }

    public EmitenteBuilder addCNPJ(String CNPJ) {
	this.emit.setCNPJ(CNPJ);
	return this;
    }

    public EmitenteBuilder addxNome(String xNome) {
	this.emit.setxNome(xNome);
	return this;
    }

    public EmitenteBuilder addxFant(String xFant) {
	this.emit.setxFant(xFant);
	return this;
    }

    public EmitenteBuilder addEnderEmit(Endereco enderEmit) {
	this.emit.setEnderEmit(enderEmit);
	return this;
    }

    public EmitenteBuilder addIE(String IE) {
	this.emit.setIE(IE);
	return this;
    }

    public EmitenteBuilder addIEST(String IEST) {
	this.emit.setIEST(IEST);
	return this;
    }

    public EmitenteBuilder addIM(String IM) {
	this.emit.setIM(IM);
	return this;
    }

    public EmitenteBuilder addCNAE(String CNAE) {
	this.emit.setCNAE(CNAE);
	return this;
    }

    public EmitenteBuilder addCRT(String CRT) {
	this.emit.setCRT(CRT);
	return this;
    }

    public EmitenteBuilder addNf_serie_atual(Integer nf_serie_atual) {
	this.emit.setNf_serie_atual(nf_serie_atual);
	return this;
    }

    public EmitenteBuilder addUltima_nnf(Integer ultima_nnf) {
	this.emit.setUltima_nnf(ultima_nnf);
	return this;
    }

    public Emitente get() {
	return this.emit;
    }
}