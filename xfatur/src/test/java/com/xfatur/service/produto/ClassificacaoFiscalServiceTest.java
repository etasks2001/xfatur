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

import com.xfatur.exception.ClassificacaoFiscalIdNotFoundException;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ClassificacaoFiscalServiceTest {

    @Autowired
    ClassificacaoFiscalService classificacaoFiscalService;

    List<Integer> ids = new ArrayList<Integer>();

    @AfterAll
    void delete() {
	ids.forEach(id -> classificacaoFiscalService.deleteById(id));
    }

    @Test
    @Order(1)
    void test_save() {
	CreateModelTest.classificacaoFiscalList().forEach(classificacaoFiscal -> {
	    Integer id = classificacaoFiscalService.findIdByDescricao(classificacaoFiscal.getDescricao());
	    if (id == null) {
		ClassificacaoFiscal saved = classificacaoFiscalService.save(classificacaoFiscal);

		id = saved.getId();

		classificacaoFiscal.setId(id);
		MatcherAssert.assertThat(classificacaoFiscal.getId(), Matchers.is(saved.getId()));
	    }

	    ids.add(id);

	});
    }

    @Test
    @Order(2)
    void test_update() {
	ClassificacaoFiscal found = classificacaoFiscalService.findById(ids.get(0));

	found.setDescricao(found.getDescricao() + " alterado");

	classificacaoFiscalService.save(found);
    }

    @Test
    @Order(3)
    void test_findById() {
	ClassificacaoFiscal found = classificacaoFiscalService.findById(ids.get(0));

	assertNotNull(found);
    }

    @Test
    @Order(4)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(ClassificacaoFiscalIdNotFoundException.class, () -> classificacaoFiscalService.findById(546456));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Classificação Fiscal não encontrado."));
    }

    @Test
    @Order(5)
    void test_findByDescricao() {
	List<ClassificacaoFiscal> list = classificacaoFiscalService.findByDescricao("O");

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));

    }

    @Test
    @Order(6)
    void test_findByDescricao_size_0() {
	List<ClassificacaoFiscal> list = classificacaoFiscalService.findByDescricao("fdsafdaO");

	MatcherAssert.assertThat(list.size(), Matchers.is(0));

    }
}
