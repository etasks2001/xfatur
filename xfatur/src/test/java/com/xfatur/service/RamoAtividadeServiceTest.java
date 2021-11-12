package com.xfatur.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.exception.RamoAtividadeException;
import com.xfatur.exception.RamoAtividadeIdNotFoundException;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class RamoAtividadeServiceTest {

    @Autowired
    RamoAtividadeService service;

    static Stream<RamoAtividadeDTO> model() {
	return Stream.of(CreateModelTest.createRamoAtividade1(), CreateModelTest.createRamoAtividade2(), CreateModelTest.createRamoAtividade3(), CreateModelTest.createRamoAtividade4(),
		CreateModelTest.createRamoAtividade5(), CreateModelTest.createRamoAtividade6());

    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(RamoAtividadeDTO ra) {
	Integer id = this.service.save(ra).getId();
	ra.setId(id);

	MatcherAssert.assertThat(id, Matchers.is(ra.getId()));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
    void test_save_cnpj_ja_cadastrado(RamoAtividadeDTO e) {
	RamoAtividadeException exception = Assertions.assertThrows(RamoAtividadeException.class, () -> service.save(e));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Descrição já cadastrada"));
    }

    @Test
    @Order(3)
    void test_buscaPorDescricao() {
	List<RamoAtividadeDTO> list = this.service.buscaPorDescricao("M");

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    void test_buscaPorDescricao_total_6() {
	List<RamoAtividadeDTO> list = this.service.buscaPorDescricao("");

	MatcherAssert.assertThat(list.size(), Matchers.is(6));
    }

    @Test
    @Order(5)
    void test_buscaPorDescricao_nao_encontradao() {
	List<RamoAtividadeDTO> list = this.service.buscaPorDescricao("fdasrfsdare");

	MatcherAssert.assertThat(list.size(), Matchers.is(0));
    }

    @Test
    @Order(6)
    void test_findById() {
	Integer id = service.findAll().get(0).getId();
	Integer id2 = this.service.findById(id).getId();

	MatcherAssert.assertThat(id2, Matchers.is(id));
    }

    @Test
    @Order(7)
    void test_findById_erro() {
	Exception exception = Assertions.assertThrows(RamoAtividadeIdNotFoundException.class, () -> this.service.findById(100));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Ramo de Atividade não encontrado"));
    }

    @Test
    @Order(8)
    void delete() {
	List<RamoAtividadeDTO> findAll = service.findAll();

	findAll.forEach(ra -> {
	    Boolean apagado = this.service.delete(ra.getId());
	    MatcherAssert.assertThat(apagado, Matchers.is(TRUE));
	});

    }

    @Test
    @Order(9)
    void delete_nao_encontrado() {
	Boolean result = this.service.delete(454);

	MatcherAssert.assertThat(result, Matchers.is(FALSE));
    }

}
