package com.xfatur.service.produto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.xfatur.exception.UnidadeIdNotFoundException;
import com.xfatur.model.produto.Unidade;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class UnidadeServiceTest {

    @Autowired
    UnidadeService service;

    List<Integer> ids = new ArrayList<Integer>();

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

    @Test
    @Order(1)
    void test_save() {
	CreateModelTest.unidadeList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    @Test
    @Order(2)
    void test_update() {
	Unidade unidade = service.findById(ids.get(0));

	unidade.setDescricao(unidade.getDescricao() + " alterado");

    }

    @Test
    @Order(3)
    void test_findById() {
	Unidade unidade = service.findById(ids.get(0));

	assertNotNull(unidade);
    }

    @Test
    @Order(4)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(UnidadeIdNotFoundException.class, () -> service.findById(4564));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Unidade não encontrado"));
    }

    @Test
    @Order(5)
    void test_findByDescricao() {
	List<Unidade> unidades = service.findByDescricao("a");

	MatcherAssert.assertThat(unidades.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(6)
    void test_findbyDescricao_is_0() {
	List<Unidade> unidades = service.findByDescricao("afdsafda");

	MatcherAssert.assertThat(unidades.size(), Matchers.is(0));
    }
}
