package com.xfatur.service.preco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import com.base.BaseTest;
import com.xfatur.exception.ListaPrecoIdNotFoundException;
import com.xfatur.model.preco.Item;
import com.xfatur.model.preco.Lista;
import com.xfatur.model.produto.Produto;
import com.xfatur.service.produto.ProdutoService;

@TestInstance(value = Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Service - Lista de Preço")
class ListaServiceTest extends BaseTest {

    List<Integer> idsLista = new ArrayList<Integer>();

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ListaService listaService;

    @Autowired
    ItemService itemService;

    @Test
    @Order(1)
    @Sql(scripts = { "classpath:cadastro/produtor.sql", "classpath:cadastro/unidade.sql", "classpath:cadastro/classificacaofiscal.sql", "classpath:cadastro/tributacao.sql",
	    "classpath:cadastro/regiaoprodutora.sql", "classpath:cadastro/tipovalidade.sql", "classpath:cadastro/origem.sql", "classpath:cadastro/marca.sql", "classpath:cadastro/tipoitem.sql",
	    "classpath:cadastro/tiposelo.sql", "classpath:cadastro/linha.sql", "classpath:cadastro/pais.sql",
	    "classpath:cadastro/produto.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.ISOLATED))
    void test_save() {

	Lista listaPreco = new Lista();

	listaPreco.setCodigo("001/1");
	listaPreco.setData(LocalDate.parse("01/01/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	listaPreco.setTipo("P");
	listaPreco.setDescricao("lista");
	listaPreco.setDescontoAtacado(new BigDecimal("2.55"));
	listaPreco.setDescontoVista(new BigDecimal("3.55"));
	listaPreco.setDesconto21Dias(new BigDecimal("4.55"));
	listaPreco.setDesconto28Dias(new BigDecimal("5.55"));

	Item Item1 = new Item();

	Integer id1 = produtoService.findIdByCodigoProduto("000.11.444");
	Integer id2 = produtoService.findIdByCodigoProduto("000.11.001");
	Integer id3 = produtoService.findIdByCodigoProduto("111.11.001");
	Integer id4 = produtoService.findIdByCodigoProduto("999.11.001");

	Item1.setProduto(new Produto(id1));
	Item1.setPrecounitario(new BigDecimal("451.55"));
	Item1.setTipo("111");
	Item1.setDestacar(Boolean.FALSE);

	Item Item2 = new Item();
	Item2.setProduto(new Produto(id2));
	Item2.setPrecounitario(new BigDecimal("451.55"));
	Item2.setTipo("111");
	Item2.setDestacar(Boolean.FALSE);

	Item Item3 = new Item();
	Item3.setProduto(new Produto(id3));
	Item3.setPrecounitario(new BigDecimal("451.55"));
	Item3.setTipo("111");
	Item3.setDestacar(Boolean.FALSE);

	Item Item4 = new Item();
	Item4.setProduto(new Produto(id4));
	Item4.setPrecounitario(new BigDecimal("451.55"));
	Item4.setTipo("111");
	Item4.setDestacar(Boolean.FALSE);

	listaPreco.addItem(Item1);
	listaPreco.addItem(Item2);
	listaPreco.addItem(Item3);
	listaPreco.addItem(Item4);

	listaService.save(listaPreco);

	idsLista.add(listaPreco.getId());
    }

    @Test
    @Order(2)
    void test_findById() {
	idsLista.forEach(id -> {
	    Lista listaPreco1 = listaService.findById(id);
	    Lista listaPreco2 = listaService.findById(id);

	    MatcherAssert.assertThat(listaPreco1.getId(), Matchers.is(listaPreco2.getId()));
	    MatcherAssert.assertThat(listaPreco1.getCodigo(), Matchers.is(listaPreco2.getCodigo()));
	    MatcherAssert.assertThat(listaPreco1.getDescricao(), Matchers.is(listaPreco2.getDescricao()));
	    MatcherAssert.assertThat(listaPreco1.getDescontoVista(), Matchers.is(listaPreco2.getDescontoVista()));
	    MatcherAssert.assertThat(listaPreco1.getDescontoAtacado(), Matchers.is(listaPreco2.getDescontoAtacado()));
	    MatcherAssert.assertThat(listaPreco1.getDesconto21Dias(), Matchers.is(listaPreco2.getDesconto21Dias()));
	    MatcherAssert.assertThat(listaPreco1.getDesconto28Dias(), Matchers.is(listaPreco2.getDesconto28Dias()));
	    MatcherAssert.assertThat(listaPreco1.getData(), Matchers.is(listaPreco2.getData()));
	    MatcherAssert.assertThat(listaPreco1.getTipo(), Matchers.is(listaPreco2.getTipo()));

	    List<Item> itens1 = itemService.findItensById(id);
	    List<Item> itens2 = itemService.findItensById(id);

	    Item item1 = itens1.get(0);
	    Item item2 = itens2.get(0);

	    MatcherAssert.assertThat(item1.getId(), Matchers.is(item2.getId()));
	    MatcherAssert.assertThat(item1.getPrecounitario(), Matchers.is(item2.getPrecounitario()));
	    MatcherAssert.assertThat(item1.getTipo(), Matchers.is(item2.getTipo()));
	    MatcherAssert.assertThat(item1.getDestacar(), Matchers.is(item2.getDestacar()));
	    MatcherAssert.assertThat(item1.getProduto().getId(), Matchers.is(item2.getProduto().getId()));

	    itens1.remove(0);

	});
    }

    @Test
    @Order(3)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(ListaPrecoIdNotFoundException.class, () -> listaService.findById(415646));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Lista não encontrada"));
    }

    @Test
    @Order(4)
    void test_update() {
	Lista lista = listaService.findById(idsLista.get(0));
	lista.setDesconto21Dias(new BigDecimal("154.555"));

	listaService.save(lista);
    }

    @Test
    @Sql(scripts = { "classpath:cadastro/produto-clean.sql", "classpath:cadastro/produtor-clean.sql", "classpath:cadastro/unidade-clean.sql", "classpath:cadastro/classificacaofiscal-clean.sql",
	    "classpath:cadastro/tributacao-clean.sql", "classpath:cadastro/regiaoprodutora-clean.sql", "classpath:cadastro/tipovalidade-clean.sql", "classpath:cadastro/origem-clean.sql",
	    "classpath:cadastro/marca-clean.sql", "classpath:cadastro/tipoitem-clean.sql", "classpath:cadastro/tiposelo-clean.sql", "classpath:cadastro/linha-clean.sql",
	    "classpath:cadastro/pais-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.ISOLATED))
    @Order(5)
    void delete() {

	idsLista.forEach(id -> listaService.deleteById(id));

    }
}