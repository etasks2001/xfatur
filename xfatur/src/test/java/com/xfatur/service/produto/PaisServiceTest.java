package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.PaisIdNotFoundException;
import com.xfatur.validation.dto.cadastro.PaisDTO;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class PaisServiceTest {

    @Autowired
    PaisService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
//	CreateModelTest.paisList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void test_findByDescricao() {
//	List<Pais> pais = service.findByDescricao("A");
//
//	MatcherAssert.assertThat(pais.size(), Matchers.greaterThan(0));

    }

    // @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
//	List<Pais> pais = service.findByDescricao("fdsafd");
//	MatcherAssert.assertThat(pais.size(), Matchers.is(0));
    }

    // @Test
    @Order(4)
    void test_update() {
	PaisDTO pais1 = service.findById(ids.get(0));
	PaisDTO pais2 = service.findById(ids.get(0));

	MatcherAssert.assertThat(pais1.getSigla(), Matchers.is(pais2.getSigla()));
	MatcherAssert.assertThat(pais1.getOrigem(), Matchers.is(pais2.getOrigem()));

	pais1.setNome(pais1.getNome() + " alterado");
	pais2.setNome(pais2.getNome() + " alterado");

	service.save(pais1);
	service.save(pais2);
    }

    // @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(PaisIdNotFoundException.class, () -> service.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Pais não encontrado"));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
