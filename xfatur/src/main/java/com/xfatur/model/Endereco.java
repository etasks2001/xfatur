package com.xfatur.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String xLgr;
	private String nro;
	private String xCpl;
	private String xBairro;
	private String cMun;
	private String xMun;
	private String UF;
	private String CEP;
	private String cPais;
	private String xPais;
	private String fone;

	public String getxLgr() {
		return xLgr;
	}

	public String getNro() {
		return nro;
	}

	public String getxCpl() {
		return xCpl;
	}

	public String getxBairro() {
		return xBairro;
	}

	public String getcMun() {
		return cMun;
	}

	public String getxMun() {
		return xMun;
	}

	public String getUF() {
		return UF;
	}

	public String getCEP() {
		return CEP;
	}

	public String getcPais() {
		return cPais;
	}

	public String getxPais() {
		return xPais;
	}

	public String getFone() {
		return fone;
	}

	public void setxLgr(String xLgr) {
		this.xLgr = xLgr;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public void setxCpl(String xCpl) {
		this.xCpl = xCpl;
	}

	public void setxBairro(String xBairro) {
		this.xBairro = xBairro;
	}

	public void setcMun(String cMun) {
		this.cMun = cMun;
	}

	public void setxMun(String xMun) {
		this.xMun = xMun;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public void setcPais(String cPais) {
		this.cPais = cPais;
	}

	public void setxPais(String xPais) {
		this.xPais = xPais;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	@Override
	public String toString() {
		return "Endereco [xLgr=" + xLgr + ", nro=" + nro + ", xCpl=" + xCpl + ", xBairro=" + xBairro + ", cMun=" + cMun + ", xMun=" + xMun + ", UF=" + UF + ", CEP=" + CEP + ", cPais=" + cPais + ", xPais=" + xPais + ", fone=" + fone + "]";
	}

}
