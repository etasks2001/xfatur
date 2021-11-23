package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

import com.xfatur.exception.ProdutorIdNotFoundException;
import com.xfatur.model.produto.Produtor;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ProdutorServiceTest {

    @Autowired
    ProdutorService produtorService;

    List<Integer> ids = new ArrayList<Integer>();

    Stream<Produtor> model() {
	return CreateModelTest.produtorList();
    }

    @AfterAll
    void delete() {
	ids.forEach(id -> produtorService.deleteById(id));
    }

    @Test
    @Order(1)
    void test_save() {
	CreateModelTest.produtorList().forEach(p -> {
	    Integer id = produtorService.findByIdDescricao(p.getDescricao());
	    if (id == null) {
		Produtor saved = produtorService.save(p);

		id = saved.getId();

		p.setId(id);
		MatcherAssert.assertThat(p.getId(), Matchers.is(saved.getId()));
	    }

	    ids.add(id);
	});
    }

    @Test
    @Order(2)
    void test_findByDescricao() {
	List<Produtor> produtores = produtorService.findByDescricao("A");

	MatcherAssert.assertThat(produtores.size(), Matchers.greaterThan(0));

    }

    @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
	List<Produtor> produtores = produtorService.findByDescricao("fdsafd");
	MatcherAssert.assertThat(produtores.size(), Matchers.is(0));
    }

    @Test
    @Order(4)
    void test_update() {
	Produtor produtor = produtorService.findById(ids.get(0));

	produtor.setDescricao(produtor.getDescricao() + " alterado");

	produtorService.save(produtor);
    }

    @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(ProdutorIdNotFoundException.class, () -> produtorService.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Produtor não encontrado"));

    }

}
