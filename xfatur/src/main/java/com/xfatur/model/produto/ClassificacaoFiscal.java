package com.xfatur.model.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ClassificacaoFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String ncm;

	@Column(nullable = false, unique = true)
	private String descricao;

	@OneToMany(mappedBy = "classificacaoFiscal", cascade = CascadeType.ALL)
	private List<Produto> produtos;

	public Integer getId() {
		return id;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
