package com.xfatur.model.produto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigoProduto;
    private String descricao;
    private String unidadeDetalhada;
    private String graduacaoAlcoolica;
    private BigDecimal pesoLiquido;
    private BigDecimal pesoBruto;
    private String codigoDeBarras;
    private BigDecimal pesoDaCaixa;
    private BigDecimal larguraDaCaixa;
    private BigDecimal comprimentoDaCaixa;
    private BigDecimal ipiUnitario;
    private Boolean aliquotaDeReducao;
    private Boolean isentoICMS;
    private BigDecimal aliquotaIPI;
    private Boolean adquiridoComST;
    private String cest;

    private Integer reducaoICMS_id;
    private Integer iva_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtor_id")
    private Produtor produtor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classificacaofiscal_id")
    private ClassificacaoFiscal classificacaoFiscal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tributacao_id")
    private Tributacao tributacao;
    private Integer regiaoProdutora_id;
    private Integer linhaDeProduto_id;
    private Integer pais_id;
    private Integer tipoDeValidade_id;
    private Integer marca_id;
    private Integer origem_id;
    private String tipoProduto_id;
    private Integer fundoCombatePobreza_id;
    private String seloIPI_id;

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

    public String getUnidadeDetalhada() {
	return unidadeDetalhada;
    }

    public void setUnidadeDetalhada(String unidadeDetalhada) {
	this.unidadeDetalhada = unidadeDetalhada;
    }

    public String getGraduacaoAlcoolica() {
	return graduacaoAlcoolica;
    }

    public void setGraduacaoAlcoolica(String graduacaoAlcoolica) {
	this.graduacaoAlcoolica = graduacaoAlcoolica;
    }

    public BigDecimal getPesoLiquido() {
	return pesoLiquido;
    }

    public void setPesoLiquido(BigDecimal pesoLiquido) {
	this.pesoLiquido = pesoLiquido;
    }

    public BigDecimal getPesoBruto() {
	return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
	this.pesoBruto = pesoBruto;
    }

    public String getCodigoDeBarras() {
	return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
	this.codigoDeBarras = codigoDeBarras;
    }

    public BigDecimal getPesoDaCaixa() {
	return pesoDaCaixa;
    }

    public void setPesoDaCaixa(BigDecimal pesoDaCaixa) {
	this.pesoDaCaixa = pesoDaCaixa;
    }

    public BigDecimal getLarguraDaCaixa() {
	return larguraDaCaixa;
    }

    public void setLarguraDaCaixa(BigDecimal larguraDaCaixa) {
	this.larguraDaCaixa = larguraDaCaixa;
    }

    public BigDecimal getComprimentoDaCaixa() {
	return comprimentoDaCaixa;
    }

    public void setComprimentoDaCaixa(BigDecimal comprimentoDaCaixa) {
	this.comprimentoDaCaixa = comprimentoDaCaixa;
    }

    public BigDecimal getIpiUnitario() {
	return ipiUnitario;
    }

    public void setIpiUnitario(BigDecimal ipiUnitario) {
	this.ipiUnitario = ipiUnitario;
    }

    public Boolean getAliquotaDeReducao() {
	return aliquotaDeReducao;
    }

    public void setAliquotaDeReducao(Boolean aliquotaDeReducao) {
	this.aliquotaDeReducao = aliquotaDeReducao;
    }

    public Boolean getIsentoICMS() {
	return isentoICMS;
    }

    public void setIsentoICMS(Boolean isentoICMS) {
	this.isentoICMS = isentoICMS;
    }

    public BigDecimal getAliquotaIPI() {
	return aliquotaIPI;
    }

    public void setAliquotaIPI(BigDecimal aliquotaIPI) {
	this.aliquotaIPI = aliquotaIPI;
    }

    public Boolean getAdquiridoComST() {
	return adquiridoComST;
    }

    public void setAdquiridoComST(Boolean adquiridoComST) {
	this.adquiridoComST = adquiridoComST;
    }

    public String getCest() {
	return cest;
    }

    public void setCest(String cest) {
	this.cest = cest;
    }

    public Integer getReducaoICMS_id() {
	return reducaoICMS_id;
    }

    public void setReducaoICMS_id(Integer reducaoICMS_id) {
	this.reducaoICMS_id = reducaoICMS_id;
    }

    public Integer getIva_id() {
	return iva_id;
    }

    public void setIva_id(Integer iva_id) {
	this.iva_id = iva_id;
    }

    public Produtor getProdutor() {
	return produtor;
    }

    public void setProdutor(Produtor produtor) {
	this.produtor = produtor;
    }

    public Unidade getUnidade() {
	return unidade;
    }

    public void setUnidade(Unidade unidade) {
	this.unidade = unidade;
    }

    public ClassificacaoFiscal getClassificacaoFiscal() {
	return classificacaoFiscal;
    }

    public void setClassificacaoFiscal(ClassificacaoFiscal classificacaoFiscal) {
	this.classificacaoFiscal = classificacaoFiscal;
    }

    public Tributacao getTributacao() {
	return tributacao;
    }

    public void setTributacao(Tributacao tributacao) {
	this.tributacao = tributacao;
    }

    public Integer getRegiaoProdutora_id() {
	return regiaoProdutora_id;
    }

    public void setRegiaoProdutora_id(Integer regiaoProdutora_id) {
	this.regiaoProdutora_id = regiaoProdutora_id;
    }

    public Integer getLinhaDeProduto_id() {
	return linhaDeProduto_id;
    }

    public void setLinhaDeProduto_id(Integer linhaDeProduto_id) {
	this.linhaDeProduto_id = linhaDeProduto_id;
    }

    public Integer getPais_id() {
	return pais_id;
    }

    public void setPais_id(Integer pais_id) {
	this.pais_id = pais_id;
    }

    public Integer getTipoDeValidade_id() {
	return tipoDeValidade_id;
    }

    public void setTipoDeValidade_id(Integer tipoDeValidade_id) {
	this.tipoDeValidade_id = tipoDeValidade_id;
    }

    public Integer getMarca_id() {
	return marca_id;
    }

    public void setMarca_id(Integer marca_id) {
	this.marca_id = marca_id;
    }

    public Integer getOrigem_id() {
	return origem_id;
    }

    public void setOrigem_id(Integer origem_id) {
	this.origem_id = origem_id;
    }

    public String getTipoProduto_id() {
	return tipoProduto_id;
    }

    public void setTipoProduto_id(String tipoProduto_id) {
	this.tipoProduto_id = tipoProduto_id;
    }

    public Integer getFundoCombatePobreza_id() {
	return fundoCombatePobreza_id;
    }

    public void setFundoCombatePobreza_id(Integer fundoCombatePobreza_id) {
	this.fundoCombatePobreza_id = fundoCombatePobreza_id;
    }

    public String getSeloIPI_id() {
	return seloIPI_id;
    }

    public void setSeloIPI_id(String seloIPI_id) {
	this.seloIPI_id = seloIPI_id;
    }
}
