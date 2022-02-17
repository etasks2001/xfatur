package com.xfatur.service.produto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.ClassificacaoFiscalIdNotFoundException;
import com.xfatur.mappers.ClassificacaoFiscalMapper;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class ClassificacaoFiscalServiceTest {
    @Autowired
    private ClassificacaoFiscalMapper mapper;

    @Autowired
    ClassificacaoFiscalService service;

    List<Integer> ids = new ArrayList<Integer>();

    // @Test
    @Order(1)
    void test_save() {
	CreateModelTest.classificacaoFiscalList().forEach(entiry -> CreateModelTest.createAndIds(service, entiry, ids));
    }

    // @Test
    @Order(3)
    void test_findById() {

	ClassificacaoFiscal found1 = service.findById(ids.get(0));
	ClassificacaoFiscal found2 = service.findById(ids.get(0));

	MatcherAssert.assertThat(found1.getNcm(), Matchers.is(found2.getNcm()));
	assertNotNull(found1);
	assertNotNull(found2);
    }

    // @Test
    @Order(4)
    void test_findById_error() {
	Exception exception = Assertions.assertThrows(ClassificacaoFiscalIdNotFoundException.class, () -> service.findById(546456));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código da Classificação Fiscal não encontrado."));
    }

    // @Test
    @Order(5)
    void test_findByDescricao() {
	List<ClassificacaoFiscal> classificacoesFiscais = service.findByDescricao("O");

	MatcherAssert.assertThat(classificacoesFiscais.size(), Matchers.greaterThan(0));

    }

    // @Test
    @Order(6)
    void test_findByDescricao_size_0() {
	List<ClassificacaoFiscal> list = service.findByDescricao("fdsafdaO");

	MatcherAssert.assertThat(list.size(), Matchers.is(0));

    }

    @AfterAll
    void delete() {
	ids.forEach(id -> service.deleteById(id));
    }

}
