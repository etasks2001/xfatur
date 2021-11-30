package com.xfatur.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.SeloDuplicadoRuntimeException;
import com.xfatur.exception.SeloIdNotFoundException;
import com.xfatur.model.Selo;
import com.xfatur.model.produto.Produto;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.testutil.CreateModelTest;
import com.xfatur.testutil.UtilCreateProduto;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class SeloServiceTest {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    SeloService seloService;

    @Autowired
    UtilCreateProduto utilCreateProduto;

    List<Integer> idsProduto;

    List<Integer> idsSelo = new ArrayList<Integer>();

    @BeforeAll
    void insert_produto() {
	idsProduto = utilCreateProduto.insert();
    }

    @Test
    @Order(1)
    void test_save() {

	List<Selo> seloList = CreateModelTest.seloList().collect(Collectors.toList());

	for (int i = 0; i < seloList.size(); i++) {
	    Produto produto = produtoService.findById(idsProduto.get(i));
	    Selo selo = seloList.get(i);
	    selo.setProduto(produto);

	    Selo saved = seloService.save(selo);

	    idsSelo.add(saved.getId());

	    assertNotNull(saved);
	}

    }

    @Test
    @Order(2)
    void test_update() {
	Selo selo = seloService.findById(idsSelo.get(0));

	selo.setNumeroFinal("199999");

	seloService.save(selo);
    }

    @Test
    @Order(3)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(SeloIdNotFoundException.class, () -> seloService.findById(45646));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Selo não encontrado"));
    }

    @Test
    @Order(4)
    void test_unique() {
	Selo selo = new Selo();
	Produto produto = produtoService.findById(idsProduto.get(0));

	selo.setNumeroGuia("259/98");
	selo.setDataGuia(LocalDate.parse("04/11/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	selo.setNumeroInicial("28446664");
	selo.setNumeroFinal("28447863");
	selo.setSubstituicao(Boolean.FALSE);
	selo.setObservacao("");
	selo.setCodigoSelo("643715");
	selo.setProduto(produto);

	Exception exception = Assertions.assertThrows(SeloDuplicadoRuntimeException.class, () -> seloService.save(selo));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Selo ja cadastrado"));
    }

    @Test
    @Order(5)
    @Transactional
    void test_fields() {
	Selo selo1 = seloService.findById(idsSelo.get(0));
	Selo selo2 = seloService.findById(idsSelo.get(0));

	MatcherAssert.assertThat(selo1.getId(), Matchers.is(selo2.getId()));
	MatcherAssert.assertThat(selo1.getNumeroGuia(), Matchers.is(selo2.getNumeroGuia()));
	MatcherAssert.assertThat(selo1.getDataGuia(), Matchers.is(selo2.getDataGuia()));
	MatcherAssert.assertThat(selo1.getNumeroInicial(), Matchers.is(selo2.getNumeroInicial()));
	MatcherAssert.assertThat(selo1.getNumeroFinal(), Matchers.is(selo2.getNumeroFinal()));
	MatcherAssert.assertThat(selo1.getSubstituicao(), Matchers.is(selo2.getSubstituicao()));
	MatcherAssert.assertThat(selo1.getObservacao(), Matchers.is(selo2.getObservacao()));
	MatcherAssert.assertThat(selo1.getCodigoSelo(), Matchers.is(selo2.getCodigoSelo()));
	MatcherAssert.assertThat(selo1.getProduto(), Matchers.is(selo2.getProduto()));
    }

    @Test
    @Order(6)
    @Transactional
    void test_findAllByNumeroGuia() {
	List<Selo> list = seloService.findAllByNumeroGuiaContains("2");

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));
    }

    @AfterAll
    void delete() {
	idsSelo.forEach(id -> seloService.deleteById(id));
	utilCreateProduto.clear();
    }

}
