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

    public ClassificacaoFiscal toModel(ClassificacaoFiscalDTO dto);

    public ClassificacaoFiscalDTO toDto(ClassificacaoFiscal model);

    public Marca toModel(MarcaDTO dto);

    public MarcaDTO toDto(Marca model);

    public Origem toModel(OrigemDTO dto);

    public OrigemDTO toDto(Origem model);

    public RegiaoProdutora toModel(RegiaoProdutoraDTO dto);

    public RegiaoProdutoraDTO toDto(RegiaoProdutora model);

    public Produtor toModel(ProdutorDTO dto);

    public ProdutorDTO toDto(Produtor model);

    public TipoItem toModel(TipoItemDTO dto);

    public TipoItemDTO toDto(TipoItem model);

    public Linha toModel(LinhaDTO dto);

    public LinhaDTO toDto(Linha model);

    public TipoSelo toModel(TipoSeloDTO dto);

    public TipoSeloDTO toDto(TipoSelo model);

    public Tributacao toModel(TributacaoDTO dto);

    public TributacaoDTO toDto(Tributacao model);

    public TipoValidade toModel(TipoValidadeDTO dto);

    public TipoValidadeDTO toDto(TipoValidade model);

    public Unidade toModel(UnidadeDTO dto);

    public UnidadeDTO toDto(Unidade model);

    public Pais toModel(PaisDTO dto);

    public PaisDTO toDto(Pais model);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "codigoProduto", source = "codigoProduto")
    @Mapping(target = "descricao", source = "descricao")
    public Produto toModel(ProdutoDTO dto);

    public ProdutoDTO toDto(Produto model);

}
