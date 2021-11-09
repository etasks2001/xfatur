package com.xfatur.converter;

import com.xfatur.dto.EmitDTO;
import com.xfatur.dto.EnderDTO;
import com.xfatur.model.Emit;
import com.xfatur.model.Ender;

public class DTOConverter {

    public static EmitDTO convert(Emit emit) {
	EmitDTO emitDTO = new EmitDTO();

	emitDTO.setId(emit.getId());
	emitDTO.setCNPJ(emit.getCNPJ());
	emitDTO.setxNome(emit.getxNome());
	emitDTO.setxFant(emit.getxFant());

	emitDTO.setEnderEmit(DTOConverter.convert(emit.getEnderEmit()));
	emitDTO.setIE(emit.getIE());
	emitDTO.setIEST(emit.getIEST());
	emitDTO.setIM(emit.getIM());
	emitDTO.setCNAE(emit.getCNAE());
	emitDTO.setCRT(emit.getCRT());
	emitDTO.setNf_serie_atual(emit.getNf_serie_atual());
	emitDTO.setUltima_nnf(emit.getUltima_nnf());

	return emitDTO;
    }

    public static EnderDTO convert(Ender ender) {

	EnderDTO enderDTO = new EnderDTO();

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