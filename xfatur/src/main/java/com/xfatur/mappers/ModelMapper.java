package com.xfatur.mappers;

import org.mapstruct.Mapper;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.dto.produto.MarcaDTO;
import com.xfatur.dto.produto.OrigemDTO;
import com.xfatur.dto.produto.RegiaoProdutoraDTO;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.model.produto.Marca;
import com.xfatur.model.produto.Origem;
import com.xfatur.model.produto.RegiaoProdutora;

@Mapper(componentModel = "spring")
public interface ModelMapper {

	ClassificacaoFiscal toModel(ClassificacaoFiscalDTO dto);

	ClassificacaoFiscalDTO toDto(ClassificacaoFiscal model);

	Marca toModel(MarcaDTO dto);

	MarcaDTO toDto(Marca model);

	Origem toModel(OrigemDTO dto);

	OrigemDTO toDto(Origem model);

	RegiaoProdutora toModel(RegiaoProdutoraDTO dto);

	RegiaoProdutoraDTO toDto(RegiaoProdutora model);

}
