package com.xfatur.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Retirada {

	@Id
	private Integer id;

	@Embedded
	private Local local;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id", referencedColumnName = "id")
//	private Destinatario destinatario;

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