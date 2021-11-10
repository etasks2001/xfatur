package com.xfatur.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SerieId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id_emitente;
    private Integer serie;

    public SerieId(Integer id_emit, Integer serie) {
	this.id_emitente = id_emit;
	this.serie = serie;
    }

    public Integer getId_emit() {
	return id_emitente;
    }

    public Integer getSerie() {
	return serie;
    }
}
