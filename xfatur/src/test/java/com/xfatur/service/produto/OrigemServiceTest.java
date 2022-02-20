package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.OrigemIdNotFoundException;
import com.xfatur.model.produto.Origem;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class OrigemServiceTest {

	@Autowired
	OrigemService service;

	List<Integer> ids = new ArrayList<Integer>();

	// @Test
	@Order(1)
	void test_save() {
		CreateModelTest.origemList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
	}

	// @Test
	@Order(2)
	void test_findByDescricao() {
//	List<Origem> origem = ;//service.findByDescricao("A");

//	MatcherAssert.assertThat(origem.size(), Matchers.greaterThan(0));

	}

	// @Test
	@Order(3)
	void test_findByDescricao_retorna_vazio() {
//		List<Origem> origem = service.findByDescricao("fdsafd");
//		MatcherAssert.assertThat(origem.size(), Matchers.is(0));
	}

	// @Test
	@Order(4)
	void test_update() {
		Origem origem = service.findById(ids.get(0));

		origem.setDescricao(origem.getDescricao() + " alterado");

		service.save(origem);
	}

	// @Test
	@Order(5)
	void test_findById_nao_encontrado() {
		Exception exception = Assertions.assertThrows(OrigemIdNotFoundException.class, () -> service.findById(4567464));

		MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Origem não encontrado"));

	}

	@AfterAll
	void delete() {
		ids.forEach(id -> service.deleteById(id));
	}

}
