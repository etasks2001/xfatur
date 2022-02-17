package com.xfatur.testutil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.mappers.ClassificacaoFiscalMapper;
import com.xfatur.model.produto.FundoPobreza;
import com.xfatur.model.produto.Linha;
import com.xfatur.model.produto.Marca;
import com.xfatur.model.produto.Origem;
import com.xfatur.model.produto.Pais;
import com.xfatur.model.produto.Produtor;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.model.produto.Tipo;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.model.produto.Unidade;
import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.service.produto.FundoPobrezaService;
import com.xfatur.service.produto.LinhaService;
import com.xfatur.service.produto.MarcaService;
import com.xfatur.service.produto.OrigemService;
import com.xfatur.service.produto.PaisService;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.service.produto.ProdutorService;
import com.xfatur.service.produto.RegiaoProdutoraService;
import com.xfatur.service.produto.TipoSeloService;
import com.xfatur.service.produto.TipoService;
import com.xfatur.service.produto.TipoValidadeService;
import com.xfatur.service.produto.TributacaoService;
import com.xfatur.service.produto.UnidadeService;

@Component
public class UtilCreateProduto {
    @Autowired
    private ClassificacaoFiscalService classificacaoFiscalService;
    @Autowired
    private UnidadeService unidadeService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ProdutorService produtorService;
    @Autowired
    private TributacaoService tributacaoService;
    @Autowired
    private RegiaoProdutoraService regiaoProdutoraService;
    @Autowired
    private TipoValidadeService tipoValidadeService;
    @Autowired
    private FundoPobrezaService fundoPobrezaService;
    @Autowired
    private OrigemService origemService;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private TipoService tipoService;
    @Autowired
    private TipoSeloService tipoSeloService;
    @Autowired
    private LinhaService linhaService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private ClassificacaoFiscalMapper mapper;

    private List<String> idsTributacao = new ArrayList<String>();
    private List<Integer> idsClassificacaoFiscal = new ArrayList<Integer>();
    private List<Integer> idsProduto = new ArrayList<Integer>();
    private List<Integer> idsProdutor = new ArrayList<Integer>();
    private List<Integer> idsUnidade = new ArrayList<Integer>();
    private List<Integer> idsRegiaoProdutora = new ArrayList<Integer>();
    private List<Integer> idsTipoValidade = new ArrayList<Integer>();
    private List<Integer> idsFundoPobreza = new ArrayList<Integer>();
    private List<Integer> idsOrigem = new ArrayList<Integer>();
    private List<Integer> idsMarca = new ArrayList<Integer>();
    private List<String> idsTipo = new ArrayList<String>();
    private List<String> idsTipoSelo = new ArrayList<String>();
    private List<Integer> idsLinha = new ArrayList<Integer>();
    private List<Integer> idsPais = new ArrayList<Integer>();

    public List<Integer> idsProduto() {

	gravarTabelas();
	CreateModelTest.produtoList().forEach(produto -> {
	    int produtor_id = CreateModelTest.getCodigoAleatorio(idsProdutor);
	    int unidade_id = CreateModelTest.getCodigoAleatorio(idsUnidade);
	    int classificacaoFiscal_id = CreateModelTest.getCodigoAleatorio(idsClassificacaoFiscal);
	    String tributacao_id = CreateModelTest.getCodigoAleatorio(idsTributacao);
	    int regiaoProdutora_id = CreateModelTest.getCodigoAleatorio(idsRegiaoProdutora);
	    int tipoValidade_id = CreateModelTest.getCodigoAleatorio(idsTipoValidade);
	    int fundoPobreza_id = CreateModelTest.getCodigoAleatorio(idsFundoPobreza);
	    int marca_id = CreateModelTest.getCodigoAleatorio(idsMarca);
	    int origem_id = CreateModelTest.getCodigoAleatorio(idsOrigem);
	    String tipo_id = CreateModelTest.getCodigoAleatorio(idsTipo);
	    String tipoSelo_id = CreateModelTest.getCodigoAleatorio(idsTipoSelo);
	    int linha_id = CreateModelTest.getCodigoAleatorio(idsLinha);
	    int pais_id = CreateModelTest.getCodigoAleatorio(idsPais);

	    Produtor produtor = produtorService.findById(produtor_id);
	    Unidade unidade = unidadeService.findById(unidade_id);
	    ClassificacaoFiscalDTO classificacaoFiscal = classificacaoFiscalService.findById(classificacaoFiscal_id);
	    Tributacao tributacao = tributacaoService.findById(tributacao_id);
	    RegiaoProdutora regiaoProdutora = regiaoProdutoraService.findById(regiaoProdutora_id);
	    TipoValidade tipoValidade = tipoValidadeService.findById(tipoValidade_id);
	    FundoPobreza fundoPobreza = fundoPobrezaService.findById(fundoPobreza_id);
	    Marca marca = marcaService.findById(marca_id);
	    Origem origem = origemService.findById(origem_id);
	    Tipo tipo = tipoService.findById(tipo_id);
	    TipoSelo tipoSelo = tipoSeloService.findById(tipoSelo_id);
	    Linha linha = linhaService.findById(linha_id);
	    Pais pais = paisService.findById(pais_id);

	    produto.setProdutor(produtor);
	    produto.setUnidade(unidade);
	    produto.setClassificacaoFiscal(mapper.toModel(classificacaoFiscal));
	    produto.setTributacao(tributacao);
	    produto.setRegiaoProdutora(regiaoProdutora);
	    produto.setTipoValidade(tipoValidade);
	    produto.setFundoPobreza(fundoPobreza);
	    produto.setMarca(marca);
	    produto.setOrigem(origem);
	    produto.setTipo(tipo);
	    produto.setTipoSelo(tipoSelo);
	    produto.setLinha(linha);
	    produto.setPais(pais);

	    produtoService.save(produto);
	    System.out.println("saved: " + produto.getId());

	    idsProduto.add(produto.getId());

	});
	return (List<Integer>) idsProduto;
    }

    private void gravarTabelas() {
	CreateModelTest.produtorList().forEach(entity -> CreateModelTest.createAndIds(produtorService, entity, idsProdutor));
	CreateModelTest.unidadeList().forEach(entity -> CreateModelTest.createAndIds(unidadeService, entity, idsUnidade));
	CreateModelTest.classificacaoFiscalList().forEach(entity -> CreateModelTest.createAndIds(classificacaoFiscalService, entity, idsClassificacaoFiscal));
	CreateModelTest.tributacaoList().forEach(entity -> CreateModelTest.createAndIds(tributacaoService, entity, idsTributacao));
	CreateModelTest.regiaoProdutoraList().forEach(entity -> CreateModelTest.createAndIds(regiaoProdutoraService, entity, idsRegiaoProdutora));
	CreateModelTest.tipoValidadeList().forEach(entity -> CreateModelTest.createAndIds(tipoValidadeService, entity, idsTipoValidade));

	CreateModelTest.fundoPobrezaList().forEach(entity -> CreateModelTest.createAndIds(fundoPobrezaService, entity, idsFundoPobreza));
	CreateModelTest.origemList().forEach(entity -> CreateModelTest.createAndIds(origemService, entity, idsOrigem));
	CreateModelTest.marcaList().forEach(entity -> CreateModelTest.createAndIds(marcaService, entity, idsMarca));

	CreateModelTest.tipoList().forEach(entity -> CreateModelTest.createAndIds(tipoService, entity, idsTipo));
	CreateModelTest.tipoSeloList().forEach(entity -> CreateModelTest.createAndIds(tipoSeloService, entity, idsTipoSelo));

	CreateModelTest.linhaList().forEach(entity -> CreateModelTest.createAndIds(linhaService, entity, idsLinha));
	CreateModelTest.paisList().forEach(entity -> CreateModelTest.createAndIds(paisService, entity, idsPais));
    }

    public void clear() {
	idsProduto.forEach(id -> produtoService.deleteById(id));
	idsTributacao = new ArrayList<String>();
	idsClassificacaoFiscal = new ArrayList<Integer>();
	idsProduto = new ArrayList<Integer>();
	idsProdutor = new ArrayList<Integer>();
	idsUnidade = new ArrayList<Integer>();
	idsRegiaoProdutora = new ArrayList<Integer>();
	idsTipoValidade = new ArrayList<Integer>();
	idsFundoPobreza = new ArrayList<Integer>();
	idsOrigem = new ArrayList<Integer>();
	idsMarca = new ArrayList<Integer>();
	idsTipo = new ArrayList<String>();
	idsTipoSelo = new ArrayList<String>();
	idsLinha = new ArrayList<Integer>();
	idsPais = new ArrayList<Integer>();
    }
}
