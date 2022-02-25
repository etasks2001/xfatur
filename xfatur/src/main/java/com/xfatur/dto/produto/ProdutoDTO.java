package com.xfatur.dto.produto;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.xfatur.dto.DTO;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.model.produto.Linha;
import com.xfatur.model.produto.Marca;
import com.xfatur.model.produto.Origem;
import com.xfatur.model.produto.Pais;
import com.xfatur.model.produto.Produtor;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.model.produto.TipoItem;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.model.produto.Unidade;
import com.xfatur.validation.multi.MultiFielValidate;
import com.xfatur.validation.multi.checkers.ProdutoChecker;

@Component
@MultiFielValidate(checker = ProdutoChecker.class)
public class ProdutoDTO implements DTO {

    private Integer id;
    private String codigoProduto;
    @Size(min = 3, max = 80)
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
    private Produtor produtor;
    private Unidade unidade;
    private ClassificacaoFiscal classificacaoFiscal;
    private Tributacao tributacao;
    private RegiaoProdutora regiaoProdutora;
    private Linha linha;
    private Pais pais;
    private TipoValidade tipoValidade;
    private Marca marca;
    private Origem origem;
    private TipoItem tipoItem;
    private TipoSelo tipoSelo;

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

    public RegiaoProdutora getRegiaoProdutora() {
	return regiaoProdutora;
    }

    public void setRegiaoProdutora(RegiaoProdutora regiaoProdutora) {
	this.regiaoProdutora = regiaoProdutora;
    }

    public Linha getLinha() {
	return linha;
    }

    public void setLinha(Linha linha) {
	this.linha = linha;
    }

    public Pais getPais() {
	return pais;
    }

    public void setPais(Pais pais) {
	this.pais = pais;
    }

    public TipoValidade getTipoValidade() {
	return tipoValidade;
    }

    public void setTipoValidade(TipoValidade tipoValidade) {
	this.tipoValidade = tipoValidade;
    }

    public Marca getMarca() {
	return marca;
    }

    public void setMarca(Marca marca) {
	this.marca = marca;
    }

    public Origem getOrigem() {
	return origem;
    }

    public void setOrigem(Origem origem) {
	this.origem = origem;
    }

    public TipoItem getTipoItem() {
	return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
	this.tipoItem = tipoItem;
    }

    public TipoSelo getTipoSelo() {
	return tipoSelo;
    }

    public void setTipoSelo(TipoSelo tipoSelo) {
	this.tipoSelo = tipoSelo;
    }

}
