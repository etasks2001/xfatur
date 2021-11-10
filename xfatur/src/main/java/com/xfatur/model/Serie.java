package com.xfatur.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Serie {

    @EmbeddedId
    private SerieId id;
    private Integer nnf_inicial;
    private Integer nnf_final;

    public Serie() {
    }

    public Serie(SerieId id, Integer nnf_inicial, Integer nnf_final) {
	super();
	this.id = id;
	this.nnf_inicial = nnf_inicial;
	this.nnf_final = nnf_final;
    }

    public SerieId getId() {
	return id;
    }

    public Integer getNnf_inicial() {
	return nnf_inicial;
    }

    public Integer getNnf_final() {
	return nnf_final;
    }

}
