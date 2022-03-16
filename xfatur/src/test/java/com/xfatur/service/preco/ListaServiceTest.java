package com.xfatur.service.preco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ListaPrecoIdNotFoundException;
import com.xfatur.model.preco.Item;
import com.xfatur.model.preco.Lista;
import com.xfatur.model.produto.Produto;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.testutil.UtilCreateProduto;

//@Sql(scripts = "classpath:chema-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(value = Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ListaServiceTest {

    List<Integer> idsLista = new ArrayList<Integer>();
    @Autowired
    UtilCreateProduto utilCreateProduto;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ListaService listaService;

    List<Integer> idsProduto;

    @Autowired
    ItemService itemService;

    @BeforeAll
    void insert() {
	idsProduto = utilCreateProduto.idsProduto();
    }

    @Test
    @Order(1)
    @Sql({ "classpath:produtor.sql" })
    @Sql(value = { "classpath:produtor-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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

	Item1.setProduto(new Produto(1));
	Item1.setPrecounitario(new BigDecimal("451.55"));
	Item1.setTipo("111");
	Item1.setDestacar(Boolean.FALSE);

	Item Item2 = new Item();
	Item2.setProduto(new Produto(2));
	Item2.setPrecounitario(new BigDecimal("451.55"));
	Item2.setTipo("111");
	Item2.setDestacar(Boolean.FALSE);

	Item Item3 = new Item();
	Item3.setProduto(new Produto(3));
	Item3.setPrecounitario(new BigDecimal("451.55"));
	Item3.setTipo("111");
	Item3.setDestacar(Boolean.FALSE);

	Item Item4 = new Item();
	Item4.setProduto(new Produto(4));
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
    @Transactional
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

//	    List<Item> item = listaPreco1.getItens();
//	    System.out.println(item.size());

	    Item item1 = listaPreco1.getItens().get(0);
	    Item item2 = listaPreco2.getItens().get(0);

	    MatcherAssert.assertThat(item1.getId(), Matchers.is(item2.getId()));
	    MatcherAssert.assertThat(item1.getPrecounitario(), Matchers.is(item2.getPrecounitario()));
	    MatcherAssert.assertThat(item1.getTipo(), Matchers.is(item2.getTipo()));
	    MatcherAssert.assertThat(item1.getDestacar(), Matchers.is(item2.getDestacar()));
	    MatcherAssert.assertThat(item1.getProduto(), Matchers.is(item2.getProduto()));

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

    @AfterAll
    void delete() {

	idsLista.forEach(id -> listaService.deleteById(id));

//	utilCreateProduto.clear();

    }
}