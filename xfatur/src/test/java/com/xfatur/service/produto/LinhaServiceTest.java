package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.LinhaIdNotFoundException;
import com.xfatur.validation.dto.cadastro.LinhaDTO;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class LinhaServiceTest {

    @Autowired
    LinhaService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
//	CreateModelTest.linhaList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void test_findByDescricao() {
//	List<Linha> linha = service.findByDescricao("A");
//
//	MatcherAssert.assertThat(linha.size(), Matchers.greaterThan(0));

    }

    // @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
//	List<Linha> linha = service.findByDescricao("fdsafd");
//	MatcherAssert.assertThat(linha.size(), Matchers.is(0));
    }

    // @Test
    @Order(4)
    void test_update() {
	LinhaDTO linha1 = service.findById(ids.get(0));
	LinhaDTO linha2 = service.findById(ids.get(0));

	MatcherAssert.assertThat(linha1.getTipo(), Matchers.is(linha2.getTipo()));
	MatcherAssert.assertThat(linha1.getOrdem(), Matchers.is(linha2.getOrdem()));

	linha1.setDescricao(linha1.getDescricao());
	linha2.setDescricao(linha2.getDescricao());

	service.save(linha1);
	service.save(linha2);
    }

    // @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(LinhaIdNotFoundException.class, () -> service.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Linha não encontrado"));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
