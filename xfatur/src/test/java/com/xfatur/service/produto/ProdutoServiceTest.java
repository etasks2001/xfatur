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
import com.xfatur.exception.ProdutoIdNotFoundException;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.model.produto.Produto;
import com.xfatur.model.produto.Produtor;
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

    List<Integer> idsClassificacaoFiscal = new ArrayList<Integer>();
    List<Integer> idsProduto = new ArrayList<Integer>();
    List<Integer> idsProdutor = new ArrayList<Integer>();
    List<Integer> idsUnidade = new ArrayList<Integer>();

    Stream<Produto> model() {
	return Stream.of(CreateModelTest.createProduto1(), CreateModelTest.createProduto2());
    }

    @AfterAll
    void delete() {
	idsProduto.forEach(id -> produtoService.deleteById(id));
	idsProdutor.forEach(id -> produtorService.deleteById(id));
    }

    @BeforeAll
    void insert() {
	CreateModelTest.produtorList().forEach(p -> {
	    Integer id = produtorService.findByIdDescricao(p.getDescricao());
	    if (id == null) {
		Produtor saved = produtorService.save(p);
		id = saved.getId();
	    }

	    idsProdutor.add(id);
	});

	CreateModelTest.unidadeList().forEach(u -> {
	    Integer id = unidadeService.findIdByAbreviacao(u.getAbreviacao());
	    if (id == null) {

		Unidade save = unidadeService.save(u);
		id = save.getId();
	    }
	    idsUnidade.add(id);

	});

	CreateModelTest.classificacaoFiscalList().forEach(classificacaoFiscal -> {

	    Integer id = classificacaoFiscalService.findIdByDescricao(classificacaoFiscal.getDescricao());
	    if (id == null) {
		ClassificacaoFiscal saved = classificacaoFiscalService.save(classificacaoFiscal);
		id = saved.getId();
	    }
	    idsClassificacaoFiscal.add(id);

	});
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(Produto produto) {
	int produtor_id = CreateModelTest.getCodigoAleatorio(idsProdutor);
	int unidade_id = CreateModelTest.getCodigoAleatorio(idsUnidade);
	int classificacaoFiscal_id = CreateModelTest.getCodigoAleatorio(idsClassificacaoFiscal);

	Produtor produtor = produtorService.findById(produtor_id);
	Unidade unidade = unidadeService.findById(unidade_id);
	ClassificacaoFiscal classificacaoFiscal = classificacaoFiscalService.findById(classificacaoFiscal_id);

	produto.setProdutor(produtor);
	produto.setUnidade(unidade);
	produto.setClassificacaoFiscal(classificacaoFiscal);

	Produto saved = produtoService.save(produto);

	idsProduto.add(saved.getId());

	System.out.println("incluido:" + saved.getId());

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
	MatcherAssert.assertThat(saved.getClassificacaoFiscal(), Matchers.is(produto.getClassificacaoFiscal()));
	MatcherAssert.assertThat(saved.getFundoCombatePobreza_id(), Matchers.is(produto.getFundoCombatePobreza_id()));
	MatcherAssert.assertThat(saved.getUnidadeDetalhada(), Matchers.is(produto.getUnidadeDetalhada()));
	MatcherAssert.assertThat(saved.getGraduacaoAlcoolica(), Matchers.is(produto.getGraduacaoAlcoolica()));
	MatcherAssert.assertThat(saved.getCodigoDeBarras(), Matchers.is(produto.getCodigoDeBarras()));
	MatcherAssert.assertThat(saved.getLarguraDaCaixa(), Matchers.is(produto.getLarguraDaCaixa()));
	MatcherAssert.assertThat(saved.getComprimentoDaCaixa(), Matchers.is(produto.getComprimentoDaCaixa()));
	MatcherAssert.assertThat(saved.getAliquotaDeReducao(), Matchers.is(produto.getAliquotaDeReducao()));
	MatcherAssert.assertThat(saved.getAdquiridoComST(), Matchers.is(produto.getAdquiridoComST()));
	MatcherAssert.assertThat(saved.getReducaoICMS_id(), Matchers.is(produto.getReducaoICMS_id()));
	MatcherAssert.assertThat(saved.getCodigoDeTributacao_id(), Matchers.is(produto.getCodigoDeTributacao_id()));
	MatcherAssert.assertThat(saved.getRegiaoProdutora_id(), Matchers.is(produto.getRegiaoProdutora_id()));
	MatcherAssert.assertThat(saved.getLinhaDeProduto_id(), Matchers.is(produto.getLinhaDeProduto_id()));
	MatcherAssert.assertThat(saved.getTipoDeValidade_id(), Matchers.is(produto.getTipoDeValidade_id()));
	MatcherAssert.assertThat(saved.getTipoProduto_id(), Matchers.is(produto.getTipoProduto_id()));
	MatcherAssert.assertThat(saved.getPais_id(), Matchers.is(produto.getPais_id()));
	MatcherAssert.assertThat(saved.getMarca_id(), Matchers.is(produto.getMarca_id()));
	MatcherAssert.assertThat(saved.getOrigem_id(), Matchers.is(produto.getOrigem_id()));
	MatcherAssert.assertThat(saved.getSeloIPI_id(), Matchers.is(produto.getSeloIPI_id()));

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
	List<Produto> produtos = produtoService.buscaPorDescricao("a");

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
}