package com.xfatur.mappers;

import org.mapstruct.Mapper;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.model.produto.ClassificacaoFiscal;

@Mapper(componentModel = "spring")
public interface ClassificacaoFiscalMapper {

    ClassificacaoFiscal toModel(ClassificacaoFiscalDTO dto);

    ClassificacaoFiscalDTO toDto(ClassificacaoFiscal model);

}
