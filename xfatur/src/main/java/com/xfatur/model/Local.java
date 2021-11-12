package com.xfatur.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Local {
    @Embedded
    private Endereco endereco;
    @Embedded
    private Pessoa pessoa;

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

}