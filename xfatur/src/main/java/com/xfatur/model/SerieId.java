package com.xfatur.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SerieId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id_emitente;
    private Integer serie;

    public SerieId() {
    }

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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id_emitente == null) ? 0 : id_emitente.hashCode());
	result = prime * result + ((serie == null) ? 0 : serie.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SerieId other = (SerieId) obj;
	if (id_emitente == null) {
	    if (other.id_emitente != null)
		return false;
	} else if (!id_emitente.equals(other.id_emitente))
	    return false;
	if (serie == null) {
	    if (other.serie != null)
		return false;
	} else if (!serie.equals(other.serie))
	    return false;
	return true;
    }

}
