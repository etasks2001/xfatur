package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.ProdutorIdNotFoundException;
import com.xfatur.model.produto.Produtor;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class ProdutorServiceTest {

    @Autowired
    ProdutorService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
	CreateModelTest.produtorList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void test_findByDescricao() {
	List<Produtor> produtores = service.findByDescricao("A");

	MatcherAssert.assertThat(produtores.size(), Matchers.greaterThan(0));

    }

    // @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
	List<Produtor> produtores = service.findByDescricao("fdsafd");
	MatcherAssert.assertThat(produtores.size(), Matchers.is(0));
    }

    // @Test
    @Order(4)
    void test_update() {
	Produtor produtor = service.findById(ids.get(0));

	produtor.setDescricao(produtor.getDescricao() + " alterado");

	service.save(produtor);
    }

    // @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(ProdutorIdNotFoundException.class, () -> service.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Produtor não encontrado"));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
