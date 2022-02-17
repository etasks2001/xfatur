package com.xfatur.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.RamoAtividadeDescricaoException;
import com.xfatur.exception.RamoAtividadeIdNotFoundException;
import com.xfatur.model.RamoAtividade;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class RamoAtividadeServiceTest {
    @Autowired
    RamoAtividadeService service;

    List<Integer> ids = new ArrayList<Integer>();

    static Stream<RamoAtividade> model() {
	return Stream.of(CreateModelTest.createRamoAtividade1(), CreateModelTest.createRamoAtividade2(), CreateModelTest.createRamoAtividade3(), CreateModelTest.createRamoAtividade4(),
		CreateModelTest.createRamoAtividade5(), CreateModelTest.createRamoAtividade6());

    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(1)
    void test_save(RamoAtividade ra) {
	Integer id = service.findIdByDescricao(ra.getDescricao());

	if (id == null) {
	    RamoAtividade saved = service.save(ra);
	    id = saved.getId();
	}
	ids.add(id);
    }

    // @Test
    @Order(2)
    void test_update() {
	ids.forEach(id -> {
	    RamoAtividade ramoAtividade = service.findById(id);
	    ramoAtividade.setDescricao(ramoAtividade.getDescricao() + " alterado");
	    service.save(ramoAtividade);
	});
    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(3)
    void test_save_ja_cadastrado(RamoAtividade ramoAtividade) {
	ramoAtividade.setDescricao(ramoAtividade.getDescricao() + " alterado");

	RamoAtividadeDescricaoException exception = Assertions.assertThrows(RamoAtividadeDescricaoException.class, () -> service.save(ramoAtividade));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Descrição já cadastrada"));
    }

    // @Test
    @Order(4)
    void test_buscaPorDescricao() {
	List<RamoAtividade> list = this.service.buscaPorDescricao("M");

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));
    }

    // @Test
    @Order(5)
    void test_buscaPorDescricao_nao_encontradao() {
	List<RamoAtividade> list = this.service.buscaPorDescricao("fdasrfsdare");

	MatcherAssert.assertThat(list.size(), Matchers.is(0));
    }

    // @Test
    @Order(6)
    void test_findById() {
	ids.forEach(id -> {
	    RamoAtividade ramoAtividade = this.service.findById(id);
	    assertNotNull(ramoAtividade);
	});
    }

    // @Test
    @Order(7)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(RamoAtividadeIdNotFoundException.class, () -> this.service.findById(100));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Ramo de Atividade não encontrado"));
    }

    // @Test
    @Order(8)
    void delete() {
	ids.forEach(id -> {
	    Boolean apagado = service.delete(id);

	    assertEquals(apagado, TRUE);
	});
    }

    // @Test
    @Order(9)
    void delete_nao_encontrado() {
	Boolean result = this.service.delete(454);

	assertEquals(result, FALSE);
    }
}