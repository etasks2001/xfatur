package com.xfatur.util;

import com.xfatur.model.Endereco;

public class EnderecoBuilder {
    private Endereco ender;

    private EnderecoBuilder() {
	this.ender = new Endereco();
    }

    public static EnderecoBuilder builder() {
	return new EnderecoBuilder();
    }

    public EnderecoBuilder addxLgr(String xLgr) {
	this.ender.setxLgr(xLgr);
	return this;
    }

    public EnderecoBuilder addNro(String nro) {
	this.ender.setNro(nro);
	return this;
    }

    public EnderecoBuilder addxCpl(String xCpl) {
	this.ender.setxCpl(xCpl);
	return this;
    }

    public EnderecoBuilder addxBairro(String xBairro) {
	this.ender.setxBairro(xBairro);
	return this;
    }

    public EnderecoBuilder addcMun(String cMun) {
	this.ender.setcMun(cMun);
	return this;
    }

    public EnderecoBuilder addxMun(String xMun) {
	this.ender.setxMun(xMun);
	return this;
    }

    public EnderecoBuilder addUF(String UF) {
	this.ender.setUF(UF);
	return this;
    }

    public EnderecoBuilder addCEP(String CEP) {
	this.ender.setCEP(CEP);
	return this;
    }

    public EnderecoBuilder addcPais(String cPais) {
	this.ender.setcPais(cPais);
	return this;
    }

    public EnderecoBuilder addxPais(String xPais) {
	this.ender.setxPais(xPais);
	return this;
    }

    public EnderecoBuilder addFone(String fone) {
	this.ender.setFone(fone);
	return this;
    }

    public Endereco get() {
	return this.ender;
    }
}
