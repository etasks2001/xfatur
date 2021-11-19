package com.xfatur.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CNPJCPF;
    private String xNome;
    @Embedded
    private Endereco endereco;
    private String IE;
    private String email;

    @OneToMany(mappedBy = "representante", fetch = FetchType.LAZY)
    private List<Destinatario> destinatarios;

    public Integer getId() {
	return id;
    }

    public String getCNPJCPF() {
	return CNPJCPF;
    }

    public void setCNPJCPF(String cNPJCPF) {
	CNPJCPF = cNPJCPF;
    }

    public String getxNome() {
	return xNome;
    }

    public void setxNome(String xNome) {
	this.xNome = xNome;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public String getIE() {
	return IE;
    }

    public void setIE(String iE) {
	IE = iE;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public List<Destinatario> getDestinatario() {
	return destinatarios;
    }

}
