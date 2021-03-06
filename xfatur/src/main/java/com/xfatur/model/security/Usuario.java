package com.xfatur.model.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(indexes = { @Index(name = "idx_usuario_email", columnList = "email") })
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuarios_tem_perfis", joinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "id") }, inverseJoinColumns = {
	    @JoinColumn(name = "perfil_id", referencedColumnName = "id") })
    private List<Perfil> perfis;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "codigo_verificador", length = 6)
    private String codigoVerificador;

    public Usuario() {
	super();
    }

    public void addPerfil(PerfilTipo tipo) {
	if (this.perfis == null) {
	    this.perfis = new ArrayList<>();
	}
	this.perfis.add(new Perfil(tipo.getCodigo()));
    }

    public Usuario(String email) {
	this.email = email;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public List<Perfil> getPerfis() {
	return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
	this.perfis = perfis;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

    public String getCodigoVerificador() {
	return codigoVerificador;
    }

    public void setCodigoVerificador(String codigoVerificador) {
	this.codigoVerificador = codigoVerificador;
    }

    public boolean hasNotId() {
	return id == null;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", ativo=" + ativo + ", codigoVerificador=" + codigoVerificador + "]";
    }

}
