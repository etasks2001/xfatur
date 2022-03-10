package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.TipoIdNotFoundException;
import com.xfatur.testutil.CreateModelTest;
import com.xfatur.validation.dto.cadastro.TipoItemDTO;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class TipoServiceTest {

    @Autowired
    TipoItemService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
	CreateModelTest.tipoList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void update() {
	ids.forEach(id -> {
	    TipoItemDTO tipo = service.findById(id);

	    tipo.setDescricao(tipo.getDescricao() + " A");

	    service.save(tipo);

	});

    }

    // @Test
    @Order(3)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(TipoIdNotFoundException.class, () -> service.findById(78));
	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Tipo não encontrado"));
    }

    // @Test
    @Order(4)
    void test_findByDescricao() {

//	List<TipoItem> tributacoes = service.findByDescricao("A");
//
//	MatcherAssert.assertThat(tributacoes.size(), Matchers.greaterThan(0));
    }

    // @Test
    @Order(5)
    void test_findByDescricao_size_0() {
//	List<TipoItem> tributacoes = service.findByDescricao("787897");
//
//	MatcherAssert.assertThat(tributacoes.size(), Matchers.is(0));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
