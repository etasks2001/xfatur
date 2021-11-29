package com.xfatur.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
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

import com.xfatur.model.EstoqueMensal;
import com.xfatur.model.produto.Produto;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.testutil.UtilCreateProduto;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
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
	utilCreateProduto.insert();
	idsProduto = utilCreateProduto.getIdsProduto();
    }

    @Test
    @Order(1)
    void test_save() {
	EstoqueMensal estoqueMensal1 = new EstoqueMensal();
	Produto produto = produtoService.findById(idsProduto.get(0));

	estoqueMensal1.setProduto(produto);
	estoqueMensal1.setAno(2001);
	estoqueMensal1.setMes(1);
	estoqueMensal1.setQuantidadeInicial(150);
	estoqueMensal1.setCustoUnitario(new BigDecimal("154.44"));

	EstoqueMensal saved = estoqueMensalService.save(estoqueMensal1);

	idsEstoqueMensal.add(saved.getId());

	assertNotNull(saved);
    }

    @AfterAll
    void delete() {
	idsEstoqueMensal.forEach(id -> estoqueMensalService.deleteById(id));
	utilCreateProduto.clear();
    }

}
