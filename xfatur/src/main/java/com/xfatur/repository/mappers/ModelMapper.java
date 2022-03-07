package com.xfatur.repository.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;
import com.xfatur.validation.dto.cadastro.LinhaDTO;
import com.xfatur.validation.dto.cadastro.MarcaDTO;
import com.xfatur.validation.dto.cadastro.OrigemDTO;
import com.xfatur.validation.dto.cadastro.PaisDTO;
import com.xfatur.validation.dto.cadastro.ProdutoDTO;
import com.xfatur.validation.dto.cadastro.ProdutorDTO;
import com.xfatur.validation.dto.cadastro.RegiaoProdutoraDTO;
import com.xfatur.validation.dto.cadastro.TipoItemDTO;
import com.xfatur.validation.dto.cadastro.TipoSeloDTO;
import com.xfatur.validation.dto.cadastro.TipoValidadeDTO;
import com.xfatur.validation.dto.cadastro.TributacaoDTO;
import com.xfatur.validation.dto.cadastro.UnidadeDTO;

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
    Produto toModel(ProdutoDTO dto);

    ProdutoDTO toDto(Produto model);

}
