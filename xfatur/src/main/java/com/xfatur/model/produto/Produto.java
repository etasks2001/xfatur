package com.xfatur.model.produto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.xfatur.model.EstoqueMensal;
import com.xfatur.model.Selo;
import com.xfatur.model.preco.Item;

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

    @Column(insertable = false, updatable = false)
    private Integer estoque;

    @Column(insertable = false, updatable = false)
    private Integer reservado;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiaoprodutora_id")
    private RegiaoProdutora regiaoProdutora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linha_id")
    private Linha linha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipovalidade_id")
    private TipoValidade tipoValidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id")
    private Origem origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private TipoItem tipoItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiposelo_id")
    private TipoSelo tipoSelo;

    @OneToMany(mappedBy = "produto")
    private List<EstoqueMensal> estoqueMensal;

    @OneToMany(mappedBy = "produto")
    private List<Selo> selos;

    @OneToMany(mappedBy = "produto")
    private List<Item> itens;

    public Produto(int id) {
	this.id = id;
    }

    public Produto() {
    }

    public Integer getId() {
	return id;
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

    public Integer getEstoque() {
	return estoque;
    }

    public void setEstoque(Integer estoque) {
	this.estoque = estoque;
    }

    public Integer getReservado() {
	return reservado;
    }

    public void setReservado(Integer reservado) {
	this.reservado = reservado;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}