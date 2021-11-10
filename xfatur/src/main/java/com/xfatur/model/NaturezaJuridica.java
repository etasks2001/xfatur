package com.xfatur.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.xfatur.dto.NaturezaJuridicaDTO;

@Entity
public class NaturezaJuridica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    @OneToMany(mappedBy = "naturezaJuridica")
    private List<Destinatario> destinatario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public List<Destinatario> getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(List<Destinatario> destinatario) {
	this.destinatario = destinatario;
    }

    public static NaturezaJuridica convert(NaturezaJuridicaDTO naturezaJuridicaDTO) {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setId(naturezaJuridicaDTO.getId());
	naturezaJuridica.setDescricao(naturezaJuridicaDTO.getDescricao());
	naturezaJuridica.setDestinatario(naturezaJuridicaDTO.getDestinatario());

	return naturezaJuridica;
    }

}
