package com.xfatur.converter;

import com.xfatur.dto.EmitenteDTO;
import com.xfatur.dto.EnderecoDTO;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;

public class DTOConverter {

    public static EmitenteDTO convert(Emitente emitente) {
	EmitenteDTO emitenteDTO = new EmitenteDTO();

	emitenteDTO.setId(emitente.getId());
	emitenteDTO.setCNPJ(emitente.getCNPJ());
	emitenteDTO.setxNome(emitente.getxNome());
	emitenteDTO.setxFant(emitente.getxFant());

	emitenteDTO.setEnderEmit(DTOConverter.convert(emitente.getEnderEmit()));
	emitenteDTO.setIE(emitente.getIE());
	emitenteDTO.setIEST(emitente.getIEST());
	emitenteDTO.setIM(emitente.getIM());
	emitenteDTO.setCNAE(emitente.getCNAE());
	emitenteDTO.setCRT(emitente.getCRT());
	emitenteDTO.setNf_serie_atual(emitente.getNf_serie_atual());
	emitenteDTO.setUltima_nnf(emitente.getUltima_nnf());

	return emitenteDTO;
    }

    public static EnderecoDTO convert(Endereco ender) {

	EnderecoDTO enderDTO = new EnderecoDTO();

	enderDTO.setxLgr(ender.getxLgr());
	enderDTO.setNro(ender.getNro());
	enderDTO.setxCpl(ender.getxCpl());
	enderDTO.setxBairro(ender.getxBairro());
	enderDTO.setcMun(ender.getcMun());
	enderDTO.setxMun(ender.getxMun());
	enderDTO.setUF(ender.getUF());
	enderDTO.setCEP(ender.getCEP());
	enderDTO.setcPais(ender.getcPais());
	enderDTO.setxPais(ender.getxPais());
	enderDTO.setFone(ender.getFone());

	return enderDTO;
    }
}