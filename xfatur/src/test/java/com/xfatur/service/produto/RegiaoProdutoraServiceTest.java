package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.RegiaoProdutoraIdNotFoundException;
import com.xfatur.model.produto.RegiaoProdutora;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class RegiaoProdutoraServiceTest {

	@Autowired
	RegiaoProdutoraService service;

	List<Integer> ids = new ArrayList<Integer>();

	// @Test
	@Order(1)
	void test_save() {
//		CreateModelTest.regiaoProdutoraList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
	}

	// @Test
	@Order(2)
	void test_findByDescricao() {
//	List<RegiaoProdutora> regiaoProdutora = service.findByDescricao("A");
//
//	MatcherAssert.assertThat(regiaoProdutora.size(), Matchers.greaterThan(0));
//
	}

	// @Test
	@Order(3)
	void test_findByDescricao_retorna_vazio() {
//		List<RegiaoProdutora> regiaoProdutora = service.findByDescricao("fdsafd");
//		MatcherAssert.assertThat(regiaoProdutora.size(), Matchers.is(0));
	}

	// @Test
	@Order(4)
	void test_update() {
		RegiaoProdutora regiaoProdutora = service.findById(ids.get(0));

		regiaoProdutora.setDescricao(regiaoProdutora.getDescricao());

		service.save(regiaoProdutora);
	}

	// @Test
	@Order(5)
	void test_findById_nao_encontrado() {
		Exception exception = Assertions.assertThrows(RegiaoProdutoraIdNotFoundException.class, () -> service.findById(4567464));

		MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Região Produtora não encontrado"));
	}

	@AfterAll
	void delete() {
		ids.forEach(id -> service.deleteById(id));
	}

}
