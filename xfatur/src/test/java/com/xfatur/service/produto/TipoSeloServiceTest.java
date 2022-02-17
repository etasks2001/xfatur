package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.TipoSeloIdNotFoundException;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class TipoSeloServiceTest {

    @Autowired
    TipoSeloService service;

    List<String> ids = new ArrayList<String>();

    // @Test
    @Order(1)
    void test_save() {
	CreateModelTest.tipoSeloList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void update() {
	ids.forEach(id -> {
	    TipoSelo tipoSelo = service.findById(id);

	    tipoSelo.setDescricao(tipoSelo.getDescricao() + " A");

	    service.save(tipoSelo);

	});

    }

    // @Test
    @Order(3)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(TipoSeloIdNotFoundException.class, () -> service.findById("45678979"));
	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Tipo de Selo não encontrado"));
    }

    // @Test
    @Order(4)
    void test_findByDescricao() {

	List<TipoSelo> tributacoes = service.findByDescricao("A");

	MatcherAssert.assertThat(tributacoes.size(), Matchers.greaterThan(0));
    }

    // @Test
    @Order(5)
    void test_findByDescricao_size_0() {
	List<TipoSelo> tributacoes = service.findByDescricao("787897");

	MatcherAssert.assertThat(tributacoes.size(), Matchers.is(0));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
