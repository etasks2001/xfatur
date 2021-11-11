package com.xfatur.model;

public enum IndIEDest {
    CONTRIBUINTE_ICMS("1"), CONTRIBUINTE_ISENTO_ICMS("2"), NAO_CONTRIBUINTE_ICMS("1");

    private String codigo;

    IndIEDest(String codigo) {
	this.codigo = codigo;
    }

    public String getCodigo() {
	return codigo;
    }
}