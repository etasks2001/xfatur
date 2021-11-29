package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xfatur.exception.TributacaoIdNotFoundException;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class TributacaoServiceTest {

    @Autowired
    TributacaoService service;

    List<String> ids = new ArrayList<String>();

    @Test
    @Order(1)
    void test_save() {
	CreateModelTest.tributacaoList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    @Test
    @Order(2)
    void update() {
	ids.forEach(id -> {
	    Tributacao tributacao = service.findById(id);

	    tributacao.setDescricao(tributacao.getDescricao() + " A");

	    service.save(tributacao);

	});

    }

    @Test
    @Order(3)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(TributacaoIdNotFoundException.class, () -> service.findById("45678979"));
	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Tributação não encontrado"));
    }

    @Test
    @Order(4)
    void test_findByDescricao() {

	List<Tributacao> tributacoes = service.findByDescricao("A");

	MatcherAssert.assertThat(tributacoes.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(5)
    void test_findByDescricao_size_0() {
	List<Tributacao> tributacoes = service.findByDescricao("787897");

	MatcherAssert.assertThat(tributacoes.size(), Matchers.is(0));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
