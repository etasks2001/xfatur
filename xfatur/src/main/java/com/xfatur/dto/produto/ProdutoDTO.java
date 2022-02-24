package com.xfatur.dto.produto;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.ProdutoChecker;

@Component
@MultiFielValidate(checker = ProdutoChecker.class)
public class ProdutoDTO implements DTO {

    private Integer id;
    private String codigoProduto;
    @Size(min = 3, max = 80)
    private String descricao;

//    private String unidadeDetalhada;
//    private String graduacaoAlcoolica;
//    private BigDecimal pesoLiquido;
//    private BigDecimal pesoBruto;
//    private String codigoDeBarras;
//    private BigDecimal pesoDaCaixa;
//    private BigDecimal larguraDaCaixa;
//    private BigDecimal comprimentoDaCaixa;
//    private BigDecimal ipiUnitario;
//    private Boolean aliquotaDeReducao;
//    private Boolean isentoICMS;
//    private BigDecimal aliquotaIPI;
//    private Boolean adquiridoComST;
//    private String cest;
//    private Integer reducaoICMS_id;
//    private Integer iva_id;
//    private Produtor produtor;
//    private Unidade unidade;
//    private ClassificacaoFiscal classificacaoFiscal;
//    private Tributacao tributacao;
//    private RegiaoProdutora regiaoProdutora;
//    private Linha linha;
//    private Pais pais;
//    private TipoValidade tipoValidade;
//    private Marca marca;
//    private Origem origem;
//    private TipoItem tipoItem;
//    private TipoSelo tipoSelo;
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCodigoProduto() {
	return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
	this.codigoProduto = codigoProduto;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    @Override
    public String toString() {
	return "ProdutoDTO [id=" + id + ", codigoProduto=" + codigoProduto + ", descricao=" + descricao + "]";
    }

}
