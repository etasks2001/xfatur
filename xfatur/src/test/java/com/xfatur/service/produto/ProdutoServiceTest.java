package com.xfatur.service.produto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ProdutoCodigoNotFoundException;
import com.xfatur.exception.ProdutoEstoqueInsuficienteException;
import com.xfatur.exception.ProdutoIdNotFoundException;
import com.xfatur.exception.ProdutoReservadoInsuficienteException;
import com.xfatur.model.produto.Produto;
import com.xfatur.testutil.UtilCreateProduto;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ProdutoServiceTest {
    @Autowired
    UtilCreateProduto utilCreateProduto;
    @Autowired
    ProdutoService produtoService;
    List<Integer> idsProduto = new ArrayList<Integer>();

    @BeforeAll
    void insert() {
	idsProduto = utilCreateProduto.getIdsProduto();
    }

    @Test
    @Order(1)
    @Transactional
    void test_save() {
	Integer id = idsProduto.get(0);
	Produto produto1 = produtoService.findById(id);
	Produto produto2 = produtoService.findById(produto1.getId());

	MatcherAssert.assertThat(produto1.getId(), Matchers.is(produto2.getId()));
	MatcherAssert.assertThat(produto1.getCodigoProduto(), Matchers.is(produto2.getCodigoProduto()));
	MatcherAssert.assertThat(produto1.getDescricao(), Matchers.is(produto2.getDescricao()));
	MatcherAssert.assertThat(produto1.getPesoLiquido(), Matchers.is(produto2.getPesoLiquido()));
	MatcherAssert.assertThat(produto1.getPesoBruto(), Matchers.is(produto2.getPesoBruto()));
	MatcherAssert.assertThat(produto1.getPesoDaCaixa(), Matchers.is(produto2.getPesoDaCaixa()));
	MatcherAssert.assertThat(produto1.getIpiUnitario(), Matchers.is(produto2.getIpiUnitario()));
	MatcherAssert.assertThat(produto1.getIsentoICMS(), Matchers.is(produto2.getIsentoICMS()));
	MatcherAssert.assertThat(produto1.getAliquotaIPI(), Matchers.is(produto2.getAliquotaIPI()));
	MatcherAssert.assertThat(produto1.getIva_id(), Matchers.is(produto2.getIva_id()));

	MatcherAssert.assertThat(produto1.getProdutor(), Matchers.is(produto2.getProdutor()));
	MatcherAssert.assertThat(produto1.getUnidade(), Matchers.is(produto2.getUnidade()));
	MatcherAssert.assertThat(produto1.getCest(), Matchers.is(produto2.getCest()));
	MatcherAssert.assertThat(produto1.getEstoque(), Matchers.is(produto2.getEstoque()));
	MatcherAssert.assertThat(produto1.getReservado(), Matchers.is(produto2.getReservado()));

	MatcherAssert.assertThat(produto1.getClassificacaoFiscal(), Matchers.is(produto2.getClassificacaoFiscal()));
	MatcherAssert.assertThat(produto1.getFundoPobreza(), Matchers.is(produto2.getFundoPobreza()));
	MatcherAssert.assertThat(produto1.getUnidadeDetalhada(), Matchers.is(produto2.getUnidadeDetalhada()));
	MatcherAssert.assertThat(produto1.getGraduacaoAlcoolica(), Matchers.is(produto2.getGraduacaoAlcoolica()));
	MatcherAssert.assertThat(produto1.getCodigoDeBarras(), Matchers.is(produto2.getCodigoDeBarras()));
	MatcherAssert.assertThat(produto1.getLarguraDaCaixa(), Matchers.is(produto2.getLarguraDaCaixa()));
	MatcherAssert.assertThat(produto1.getComprimentoDaCaixa(), Matchers.is(produto2.getComprimentoDaCaixa()));
	MatcherAssert.assertThat(produto1.getAliquotaDeReducao(), Matchers.is(produto2.getAliquotaDeReducao()));
	MatcherAssert.assertThat(produto1.getAdquiridoComST(), Matchers.is(produto2.getAdquiridoComST()));
	MatcherAssert.assertThat(produto1.getReducaoICMS_id(), Matchers.is(produto2.getReducaoICMS_id()));

	MatcherAssert.assertThat(produto1.getTributacao(), Matchers.is(produto2.getTributacao()));
	MatcherAssert.assertThat(produto1.getRegiaoProdutora(), Matchers.is(produto2.getRegiaoProdutora()));
	MatcherAssert.assertThat(produto1.getLinha(), Matchers.is(produto2.getLinha()));
	MatcherAssert.assertThat(produto1.getTipoValidade(), Matchers.is(produto2.getTipoValidade()));
	MatcherAssert.assertThat(produto1.getTipo(), Matchers.is(produto2.getTipo()));
	MatcherAssert.assertThat(produto1.getPais(), Matchers.is(produto2.getPais()));
	MatcherAssert.assertThat(produto1.getMarca(), Matchers.is(produto2.getMarca()));
	MatcherAssert.assertThat(produto1.getOrigem(), Matchers.is(produto2.getOrigem()));
	MatcherAssert.assertThat(produto1.getTipoSelo(), Matchers.is(produto2.getTipoSelo()));
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
	utilCreateProduto.clear();
    }
}