package com.xfatur.mappers;

import org.mapstruct.Mapper;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.dto.produto.LinhaDTO;
import com.xfatur.dto.produto.MarcaDTO;
import com.xfatur.dto.produto.OrigemDTO;
import com.xfatur.dto.produto.ProdutorDTO;
import com.xfatur.dto.produto.RegiaoProdutoraDTO;
import com.xfatur.dto.produto.TipoItemDTO;
import com.xfatur.dto.produto.TipoSeloDTO;
import com.xfatur.dto.produto.TributacaoDTO;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.model.produto.Linha;
import com.xfatur.model.produto.Marca;
import com.xfatur.model.produto.Origem;
import com.xfatur.model.produto.Produtor;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.model.produto.TipoItem;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.model.produto.Tributacao;

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

    Produtor toModel(ProdutorDTO dto);

    ProdutorDTO toDto(Produtor model);

    TipoItem toModel(TipoItemDTO dto);

    TipoItemDTO toDto(TipoItem model);

    Linha toModel(LinhaDTO dto);

    LinhaDTO toDto(Linha model);

    TipoSelo toModel(TipoSeloDTO dto);

    TipoSeloDTO toDto(TipoSelo model);

    Tributacao toModel(TributacaoDTO dto);

    TributacaoDTO toDto(Tributacao model);

}
