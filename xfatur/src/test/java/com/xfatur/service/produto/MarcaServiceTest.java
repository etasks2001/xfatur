package com.xfatur.service.produto;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.MarcaIdNotFoundException;
import com.xfatur.testutil.CreateModelTest;
import com.xfatur.validation.dto.cadastro.MarcaDTO;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class MarcaServiceTest {

    @Autowired
    MarcaService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
	CreateModelTest.marcaList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    // @Test
    @Order(2)
    void test_findByDescricao() {
//	List<Marca> marca = service.findByDescricao("A");
//
//	MatcherAssert.assertThat(marca.size(), Matchers.greaterThan(0));

    }

    // @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
//	List<Marca> marca = service.findByDescricao("fdsafd");
//	MatcherAssert.assertThat(marca.size(), Matchers.is(0));
    }

    // @Test
    @Order(4)
    void test_update() {
	MarcaDTO marca = service.findById(ids.get(0));

	marca.setDescricao(marca.getDescricao() + " alterado");

	service.save(marca);
    }

    // @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(MarcaIdNotFoundException.class, () -> service.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Marca não encontrado"));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
