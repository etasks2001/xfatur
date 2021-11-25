package com.xfatur.service.produto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ProdutoCodigoNotFoundException;
import com.xfatur.exception.ProdutoEstoqueInsuficienteException;
import com.xfatur.exception.ProdutoIdNotFoundException;
import com.xfatur.exception.ProdutoReservadoInsuficienteException;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.model.produto.FundoPobreza;
import com.xfatur.model.produto.Linha;
import com.xfatur.model.produto.Marca;
import com.xfatur.model.produto.Origem;
import com.xfatur.model.produto.Pais;
import com.xfatur.model.produto.Produto;
import com.xfatur.model.produto.Produtor;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.model.produto.Tipo;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.model.produto.Unidade;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ProdutoServiceTest {

    @Autowired
    ClassificacaoFiscalService classificacaoFiscalService;

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutorService produtorService;

    @Autowired
    TributacaoService tributacaoService;

    @Autowired
    RegiaoProdutoraService regiaoProdutoraService;

    @Autowired
    TipoValidadeService tipoValidadeService;

    @Autowired
    FundoPobrezaService fundoPobrezaService;

    @Autowired
    OrigemService origemService;

    @Autowired
    MarcaService marcaService;
    @Autowired
    TipoService tipoService;
    @Autowired
    TipoSeloService tipoSeloService;
    @Autowired
    LinhaService linhaService;
    @Autowired
    PaisService paisService;

    List<String> idsTributacao = new ArrayList<String>();
    List<Integer> idsClassificacaoFiscal = new ArrayList<Integer>();
    List<Integer> idsProduto = new ArrayList<Integer>();
    List<Integer> idsProdutor = new ArrayList<Integer>();
    List<Integer> idsUnidade = new ArrayList<Integer>();
    List<Integer> idsRegiaoProdutora = new ArrayList<Integer>();
    List<Integer> idsTipoValidade = new ArrayList<Integer>();
    List<Integer> idsFundoPobreza = new ArrayList<Integer>();
    List<Integer> idsOrigem = new ArrayList<Integer>();
    List<Integer> idsMarca = new ArrayList<Integer>();
    List<String> idsTipo = new ArrayList<String>();
    List<String> idsTipoSelo = new ArrayList<String>();
    List<Integer> idsLinha = new ArrayList<Integer>();
    List<Integer> idsPais = new ArrayList<Integer>();

    Stream<Produto> model() {
	return Stream.of(CreateModelTest.createProduto1(), CreateModelTest.createProduto2());
    }

    @BeforeAll
    void insert() {
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

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(Produto produto) {
	Integer id = produtoService.findIdByCodigoProduto(produto.getCodigoProduto());

	if (id != null) {

	    idsProduto.add(id);
	    return;
	}

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
	ClassificacaoFiscal classificacaoFiscal = classificacaoFiscalService.findById(classificacaoFiscal_id);
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
	produto.setClassificacaoFiscal(classificacaoFiscal);
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

	Produto saved = null;
	saved = produtoService.save(produto);

	idsProduto.add(saved.getId());

	produto.setId(saved.getId());

	MatcherAssert.assertThat(saved.getId(), Matchers.is(produto.getId()));
	MatcherAssert.assertThat(saved.getCodigoProduto(), Matchers.is(produto.getCodigoProduto()));
	MatcherAssert.assertThat(saved.getDescricao(), Matchers.is(produto.getDescricao()));
	MatcherAssert.assertThat(saved.getPesoLiquido(), Matchers.is(produto.getPesoLiquido()));
	MatcherAssert.assertThat(saved.getPesoBruto(), Matchers.is(produto.getPesoBruto()));
	MatcherAssert.assertThat(saved.getPesoDaCaixa(), Matchers.is(produto.getPesoDaCaixa()));
	MatcherAssert.assertThat(saved.getIpiUnitario(), Matchers.is(produto.getIpiUnitario()));
	MatcherAssert.assertThat(saved.getIsentoICMS(), Matchers.is(produto.getIsentoICMS()));
	MatcherAssert.assertThat(saved.getAliquotaIPI(), Matchers.is(produto.getAliquotaIPI()));
	MatcherAssert.assertThat(saved.getIva_id(), Matchers.is(produto.getIva_id()));
	MatcherAssert.assertThat(saved.getProdutor(), Matchers.is(produto.getProdutor()));
	MatcherAssert.assertThat(saved.getUnidade(), Matchers.is(produto.getUnidade()));
	MatcherAssert.assertThat(saved.getCest(), Matchers.is(produto.getCest()));
	MatcherAssert.assertThat(saved.getEstoque(), Matchers.is(produto.getEstoque()));
	MatcherAssert.assertThat(saved.getReservado(), Matchers.is(produto.getReservado()));

	MatcherAssert.assertThat(saved.getClassificacaoFiscal(), Matchers.is(produto.getClassificacaoFiscal()));
	MatcherAssert.assertThat(saved.getFundoPobreza(), Matchers.is(produto.getFundoPobreza()));
	MatcherAssert.assertThat(saved.getUnidadeDetalhada(), Matchers.is(produto.getUnidadeDetalhada()));
	MatcherAssert.assertThat(saved.getGraduacaoAlcoolica(), Matchers.is(produto.getGraduacaoAlcoolica()));
	MatcherAssert.assertThat(saved.getCodigoDeBarras(), Matchers.is(produto.getCodigoDeBarras()));
	MatcherAssert.assertThat(saved.getLarguraDaCaixa(), Matchers.is(produto.getLarguraDaCaixa()));
	MatcherAssert.assertThat(saved.getComprimentoDaCaixa(), Matchers.is(produto.getComprimentoDaCaixa()));
	MatcherAssert.assertThat(saved.getAliquotaDeReducao(), Matchers.is(produto.getAliquotaDeReducao()));
	MatcherAssert.assertThat(saved.getAdquiridoComST(), Matchers.is(produto.getAdquiridoComST()));
	MatcherAssert.assertThat(saved.getReducaoICMS_id(), Matchers.is(produto.getReducaoICMS_id()));
	MatcherAssert.assertThat(saved.getTributacao(), Matchers.is(produto.getTributacao()));
	MatcherAssert.assertThat(saved.getRegiaoProdutora(), Matchers.is(produto.getRegiaoProdutora()));
	MatcherAssert.assertThat(saved.getLinha(), Matchers.is(produto.getLinha()));
	MatcherAssert.assertThat(saved.getTipoValidade(), Matchers.is(produto.getTipoValidade()));
	MatcherAssert.assertThat(saved.getTipo(), Matchers.is(produto.getTipo()));
	MatcherAssert.assertThat(saved.getPais(), Matchers.is(produto.getPais()));
	MatcherAssert.assertThat(saved.getMarca(), Matchers.is(produto.getMarca()));
	MatcherAssert.assertThat(saved.getOrigem(), Matchers.is(produto.getOrigem()));
	MatcherAssert.assertThat(saved.getTipoSelo(), Matchers.is(produto.getTipoSelo()));

    }

    @Test
    @Order(2)
    @Transactional
    void test_findById() {
	idsProduto.forEach(id -> {
	    Produto found = produtoService.findById(id);
	    assertNotNull(found);

	});

    }

    @Test
    @Order(3)
    void test_findById_error() {

	Exception exception = Assertions.assertThrows(ProdutoIdNotFoundException.class, () -> produtoService.findById(456789));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Produto não encontrado."));
    }

    @Test
    @Order(4)
    void test_update() {
	idsProduto.forEach(id -> {
	    Produto found = produtoService.findById(id);

	    found.setDescricao(found.getDescricao() + " alterado");
	    produtoService.save(found);

	});
    }

    @Test
    @Order(5)
    void test_buscaPorDescricao() {
	List<Produto> produtos = produtoService.buscaPorDescricao("A");

	MatcherAssert.assertThat(produtos.size(), Matchers.greaterThan(0));

    }

    @Test
    @Order(6)
    void test_buscaPorDescricao_nao_encontrado() {
	List<Produto> produtos = produtoService.buscaPorDescricao("fdsaa");

	MatcherAssert.assertThat(produtos.size(), Matchers.is(0));

    }

    @Test
    @Order(7)
    void test_buscaPorCodigoProduto() {
	idsProduto.forEach(id -> {
	    Produto found = produtoService.findById(id);
	    Produto produto = produtoService.findByCodigoProduto(found.getCodigoProduto());
	    assertNotNull(produto);
	});
    }

    @Test
    @Order(8)
    void test_buscaPorCodigoProduto_error() {

	Exception exception = Assertions.assertThrows(ProdutoCodigoNotFoundException.class, () -> produtoService.findByCodigoProduto("fdasfsder"));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Produto não encontrado"));
    }

    @Test
    @Order(9)
    void test_entrada_estoque() {
	Integer id = idsProduto.get(0);

	Produto produto = produtoService.findById(id);

	Integer estoqueAnterior = produto.getEstoque();

	Integer quantidadeEntrada = 4514;

	produtoService.entradaEstoque(id, quantidadeEntrada);

	produto = produtoService.findById(id);
	MatcherAssert.assertThat(produto.getEstoque(), Matchers.is(quantidadeEntrada + estoqueAnterior));

    }

    @Test
    @Order(10)
    void test_entrada_reservado() {
	Integer id = idsProduto.get(1);
	Produto produto = produtoService.findById(id);
	Integer reservadoAnterior = produto.getReservado();

	Integer quantidadeEntrada = 359;

	produtoService.entradaReservado(id, quantidadeEntrada);

	produto = produtoService.findById(id);
	MatcherAssert.assertThat(produto.getReservado(), Matchers.is(quantidadeEntrada + reservadoAnterior));

    }

    @Test
    @Order(11)
    void test_saida_estoque() {
	Integer id = idsProduto.get(0);
	Produto produto = produtoService.findById(id);
	Integer estoqueAnterior = produto.getEstoque();

	Integer quantidadeSaida = 14;

	produtoService.saidaEstoque(id, quantidadeSaida);

	produto = produtoService.findById(id);
	Integer estoqueAtual = produto.getEstoque();

	MatcherAssert.assertThat(estoqueAtual, Matchers.is(estoqueAnterior - quantidadeSaida));
    }

    @Test
    @Order(12)
    void test_saida_reservado() {
	Integer id = idsProduto.get(1);
	Produto produto = produtoService.findById(id);
	Integer reservadoAnterior = produto.getReservado();

	Integer quantidadeSaida = 59;

	produtoService.saidaReservado(id, quantidadeSaida);

	produto = produtoService.findById(id);
	Integer reservadoAtual = produto.getReservado();

	MatcherAssert.assertThat(reservadoAtual, Matchers.is(reservadoAnterior - quantidadeSaida));
    }

    @Test
    @Order(13)
    void test_saida_estoque_saldo_insuficiente() {
	Integer id = idsProduto.get(0);

	Exception exception = Assertions.assertThrows(ProdutoEstoqueInsuficienteException.class, () -> produtoService.saidaEstoque(id, 1000000));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Estoque insuficiente"));
    }

    @Test
    @Order(14)
    void test_saida_reservado_saldo_insuficiente() {
	Integer id = idsProduto.get(1);

	Exception exception = Assertions.assertThrows(ProdutoReservadoInsuficienteException.class, () -> produtoService.saidaReservado(id, 100000000));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Reservado insuficiente"));
    }

    @AfterAll
    void delete() {
	idsProduto.forEach(id -> produtoService.deleteById(id));
	idsProdutor.forEach(id -> produtorService.deleteById(id));
    }
}