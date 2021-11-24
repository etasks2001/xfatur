package com.xfatur.service.produto;

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

import com.xfatur.exception.FundoPobrezaIdNotFoundException;
import com.xfatur.model.produto.FundoPobreza;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class FundoPobrezaServiceTest {

    @Autowired
    FundoPobrezaService service;

    List<Integer> ids = new ArrayList<Integer>();

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

    @Test
    @Order(1)
    void test_save() {
	CreateModelTest.fundoPobrezaList().forEach(entity -> CreateModelTest.createAndIds(service, entity, ids));
    }

    @Test
    @Order(2)
    void test_findByDescricao() {
	List<FundoPobreza> fundoPobreza = service.findByDescricao("F");

	MatcherAssert.assertThat(fundoPobreza.size(), Matchers.greaterThan(0));

    }

    @Test
    @Order(3)
    void test_findByDescricao_retorna_vazio() {
	List<FundoPobreza> fundoPobreza = service.findByDescricao("fdsafd");
	MatcherAssert.assertThat(fundoPobreza.size(), Matchers.is(0));
    }

    @Test
    @Order(4)
    void test_update() {
	FundoPobreza fundoPobreza = service.findById(ids.get(0));

	fundoPobreza.setDescricao(fundoPobreza.getDescricao() + " alterado");

	service.save(fundoPobreza);
    }

    @Test
    @Order(5)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(FundoPobrezaIdNotFoundException.class, () -> service.findById(4567464));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Fundo Pobreza não encontrado"));

    }

}
