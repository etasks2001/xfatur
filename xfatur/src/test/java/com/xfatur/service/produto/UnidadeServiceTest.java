package com.xfatur.service.produto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.UnidadeIdNotFoundException;
import com.xfatur.validation.dto.cadastro.UnidadeDTO;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class UnidadeServiceTest {

    @Autowired
    UnidadeService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
//	CreateModelTest.unidadeList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void test_update() {
	UnidadeDTO unidade = service.findById(ids.get(0));

	unidade.setDescricao(unidade.getDescricao() + " alterado");

    }

    // @Test
    @Order(3)
    void test_findById() {
	UnidadeDTO unidade = service.findById(ids.get(0));

	assertNotNull(unidade);
    }

    // @Test
    @Order(4)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(UnidadeIdNotFoundException.class, () -> service.findById(4564));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Unidade não encontrado"));
    }

    // @Test
    @Order(5)
    void test_findByDescricao() {
//	List<Unidade> unidades = service.findByDescricao("a");
//
//	MatcherAssert.assertThat(unidades.size(), Matchers.greaterThan(0));
    }

    // @Test
    @Order(6)
    void test_findbyDescricao_is_0() {
//	List<Unidade> unidades = service.findByDescricao("afdsafda");
//
//	MatcherAssert.assertThat(unidades.size(), Matchers.is(0));
    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
