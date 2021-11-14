package com.xfatur.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
//@NamedEntityGraph(name = "Child.withParent", attributeNodes = @NamedAttributeNode("destinatario"))
public class Entrega {

	@Id
	private Integer id;

	@Embedded
	private Local local;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
	@JoinColumn(name = "id")
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

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

}