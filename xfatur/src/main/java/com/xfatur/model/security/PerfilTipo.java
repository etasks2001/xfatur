package com.xfatur.model.security;

public enum PerfilTipo {
    FINANCEIRO(1, "FINANCEIRO"), FATURAMENTO(2, "FATURAMENTO"), FISCAL(3, "FISCAL");

    private long cod;
    private String desc;

    private PerfilTipo(long cod, String desc) {
	this.cod = cod;
	this.desc = desc;
    }

    public long getCod() {
	return cod;
    }

    public String getDesc() {
	return desc;
    }
}
