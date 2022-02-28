package com.xfatur.dto.produto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.PaisChecker;

@Component
@MultiFielValidate(checker = PaisChecker.class)
public class PaisDTO implements DTO {

	private Integer id;

	@Size(min = 3, max = 80)
	private String nome;

	@Pattern(regexp = "^[A-Z][A-Z].*", message = "De A a Z")
	private String sigla;

	@Size(min = 3, max = 60)
	private String origem;

	@Pattern(regexp = "^[0-9][0-9][0-9][0-9].*", message = "Apenas números")
	private String codigoBacen;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getCodigoBacen() {
		return codigoBacen;
	}

	public void setCodigoBacen(String codigoBacen) {
		this.codigoBacen = codigoBacen;
	}

	@Override
	public String toString() {
		return "PaisDTO [id=" + id + ", nome=" + nome + "]";
	}

}
