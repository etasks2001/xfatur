package com.xfatur.model.security;

public enum PerfilTipo {
    FINANCEIRO(1, "FINANCEIRO"), FATURAMENTO(2, "FATURAMENTO"), FISCAL(3, "FISCAL");

    private int cod;
    private String desc;

    private PerfilTipo(int cod, String desc) {
	this.cod = cod;
	this.desc = desc;
    }

    public int getCod() {
	return cod;
    }

    public String getDesc() {
	return desc;
    }
}
