package com.xfatur.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.EstoqueMensalIdNotFoundException;
import com.xfatur.model.EstoqueMensal;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.testutil.UtilCreateProduto;

//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class EstoqueMensalServiceTest {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    EstoqueMensalService estoqueMensalService;

    @Autowired
    UtilCreateProduto utilCreateProduto;

    List<Integer> idsProduto;

    List<Integer> idsEstoqueMensal = new ArrayList<Integer>();

    @BeforeAll
    void insert_produto() {
//	idsProduto = utilCreateProduto.idsProduto();
    }

    // @Test
    @Order(1)
    void test_save() {

//	List<EstoqueMensal> estoqueMensalList = CreateModelTest.estoqueMensalList().collect(Collectors.toList());
//
//	for (int i = 0; i < estoqueMensalList.size(); i++) {
//	    Produto produto = produtoService.findById(idsProduto.get(i));
//	    EstoqueMensal estoqueMensal = estoqueMensalList.get(i);
//	    estoqueMensal.setProduto(produto);
//
//	    EstoqueMensal saved = estoqueMensalService.save(estoqueMensal);
//
//	    idsEstoqueMensal.add(saved.getId());
//
//	    assertNotNull(saved);
//	}

    }

    // @Test
    @Order(2)
    void test_update() {
	EstoqueMensal estoqueMensal = estoqueMensalService.findById(idsEstoqueMensal.get(0));

	estoqueMensal.setCustoUnitario(new BigDecimal("1999.99"));

	estoqueMensalService.save(estoqueMensal);
    }

    // @Test
    @Order(3)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(EstoqueMensalIdNotFoundException.class, () -> estoqueMensalService.findById(45646));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Estoque mensal não encontrado"));
    }

    // @Test
    @Order(4)
    void test_unique() {
//	EstoqueMensal estoqueMensal = new EstoqueMensal();
//	Produto produto = produtoService.findById(idsProduto.get(0));
//
//	estoqueMensal.setProduto(produto);
//	estoqueMensal.setAno(2001);
//	estoqueMensal.setMes(1);
//	estoqueMensal.setQuantidadeInicial(150);
//	estoqueMensal.setCustoUnitario(new BigDecimal("154.44"));
//
//	Exception exception = Assertions.assertThrows(EstoqueMensalDuplicadoRuntimeException.class, () -> estoqueMensalService.save(estoqueMensal));
//
//	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Produto mes e ano ja cadastrado"));
    }

    // @Test
    @Order(5)
    @Transactional
    void test_fields() {
	EstoqueMensal estoqueMensal1 = estoqueMensalService.findById(idsEstoqueMensal.get(0));
	EstoqueMensal estoqueMensal2 = estoqueMensalService.findById(idsEstoqueMensal.get(0));

	MatcherAssert.assertThat(estoqueMensal1.getAno(), Matchers.is(estoqueMensal2.getAno()));
	MatcherAssert.assertThat(estoqueMensal1.getMes(), Matchers.is(estoqueMensal2.getMes()));
	MatcherAssert.assertThat(estoqueMensal1.getQuantidadeInicial(), Matchers.is(estoqueMensal2.getQuantidadeInicial()));
	MatcherAssert.assertThat(estoqueMensal1.getCustoUnitario(), Matchers.is(estoqueMensal2.getCustoUnitario()));
	MatcherAssert.assertThat(estoqueMensal1.getProduto(), Matchers.is(estoqueMensal2.getProduto()));
    }

    // @Test
    @Order(6)
    @Transactional
    void test_findAllByMesAno() {
	List<EstoqueMensal> list = estoqueMensalService.findAllByMesAno(1, 2001);

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));
    }

    @AfterAll
    void delete() {
	idsEstoqueMensal.forEach(id -> estoqueMensalService.deleteById(id));
//	utilCreateProduto.clear();
    }

}
