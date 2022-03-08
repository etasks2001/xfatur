package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.TipoValidadeIdNotFoundException;
import com.xfatur.testutil.CreateModelTest;
import com.xfatur.validation.dto.cadastro.TipoValidadeDTO;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class TipoValidadeServiceTest {

    @Autowired
    TipoValidadeService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
	CreateModelTest.tipoValidadeList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));

    }

    // @Test
    @Order(2)
    void test_findByDescricao() {
//	List<TipoValidade> tipoValidade = service.findByDescricao("A");
//
//	MatcherAssert.assertThat(tipoValidade.size(), Matchers.greaterThan(0));

    }

    // @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
//	List<TipoValidade> tipoValidade = service.findByDescricao("fdsafd");
//	MatcherAssert.assertThat(tipoValidade.size(), Matchers.is(0));
    }

    // @Test
    @Order(4)
    void test_update() {
	TipoValidadeDTO tipoValidade = service.findById(ids.get(0));

	tipoValidade.setDescricao(tipoValidade.getDescricao());

	service.save(tipoValidade);
    }

    // @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(TipoValidadeIdNotFoundException.class, () -> service.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Tipo de Validade não encontrado"));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
