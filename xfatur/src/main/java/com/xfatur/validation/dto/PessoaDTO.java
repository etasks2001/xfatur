package com.xfatur.validation.dto;

public class PessoaDTO {
	private String xNome;
	private String CNPJCPF;
	private String IE;
	private String email;

	public String getxNome() {
		return xNome;
	}

	public void setxNome(String xNome) {
		this.xNome = xNome;
	}

	public String getCNPJCPF() {
		return CNPJCPF;
	}

	public void setCNPJCPF(String cNPJCPF) {
		CNPJCPF = cNPJCPF;
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

}
