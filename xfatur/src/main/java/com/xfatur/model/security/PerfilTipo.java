package com.xfatur.model.security;

public enum PerfilTipo {
    FINANCEIRO(1, "FINANCEIRO"), FATURAMENTO(2, "FATURAMENTO"), FISCAL(3, "FISCAL");

    private int codigo;
    private String descricao;

    private PerfilTipo(int codigo, String descricao) {
	this.codigo = codigo;
	this.descricao = descricao;
    }

    public int getCodigo() {
	return codigo;
    }

    public String getDescricao() {
	return descricao;
    }
}
