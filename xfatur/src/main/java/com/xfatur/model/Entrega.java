package com.xfatur.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Entrega {

    @Id
    private Integer id;

    @Embedded
    private Local local;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Destinatario destinatario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Local getLocal() {
	return local;
    }

    public void setLocal(Local local) {
	this.local = local;
    }
}