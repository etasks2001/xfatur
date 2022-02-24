package com.xfatur.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.dto.produto.LinhaDTO;
import com.xfatur.dto.produto.MarcaDTO;
import com.xfatur.dto.produto.OrigemDTO;
import com.xfatur.dto.produto.PaisDTO;
import com.xfatur.dto.produto.ProdutoDTO;
import com.xfatur.dto.produto.ProdutorDTO;
import com.xfatur.dto.produto.RegiaoProdutoraDTO;
import com.xfatur.dto.produto.TipoItemDTO;
import com.xfatur.dto.produto.TipoSeloDTO;
import com.xfatur.dto.produto.TipoValidadeDTO;
import com.xfatur.dto.produto.TributacaoDTO;
import com.xfatur.dto.produto.UnidadeDTO;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.model.produto.Linha;
import com.xfatur.model.produto.Marca;
import com.xfatur.model.produto.Origem;
import com.xfatur.model.produto.Pais;
import com.xfatur.model.produto.Produto;
import com.xfatur.model.produto.Produtor;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.model.produto.TipoItem;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.model.produto.Unidade;

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

    TipoValidade toModel(TipoValidadeDTO dto);

    TipoValidadeDTO toDto(TipoValidade model);

    Unidade toModel(UnidadeDTO dto);

    UnidadeDTO toDto(Unidade model);

    Pais toModel(PaisDTO dto);

    PaisDTO toDto(Pais model);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "codigoProduto", source = "codigoProduto")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "descricao", source = "descricao")
    Produto toModel(ProdutoDTO dto);

    ProdutoDTO toDto(Produto model);

}
