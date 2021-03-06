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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.NaturezaJuridicaException;
import com.xfatur.exception.NaturezaJuridicaIdNotFoundException;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
////@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class NaturezaJuridicaServiceTest {
    @Autowired
    NaturezaJuridicaService service;

    List<Integer> ids = new ArrayList<Integer>();

    static Stream<NaturezaJuridica> model() {
	return Stream.of(CreateModelTest.createNaturezaJuridica1(), CreateModelTest.createNaturezaJuridica2(), CreateModelTest.createNaturezaJuridica3(), CreateModelTest.createNaturezaJuridica4(),
		CreateModelTest.createNaturezaJuridica5());
    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(1)
    void test_save(NaturezaJuridica naturezaJuridica) {
	NaturezaJuridica saved = service.save(naturezaJuridica);
	ids.add(saved.getId());
    }

    // @Test
    @Order(2)
    void test_update() {
	ids.forEach(id -> {
	    NaturezaJuridica naturezaJuridica = service.findById(id);
	    naturezaJuridica.setDescricao(naturezaJuridica.getDescricao() + " alterado");
	    service.save(naturezaJuridica);
	});
    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(3)
    void test_save_ja_cadastrado(NaturezaJuridica naturezaJuridica) {
	naturezaJuridica.setDescricao(naturezaJuridica.getDescricao() + " alterado");

	NaturezaJuridicaException exception = Assertions.assertThrows(NaturezaJuridicaException.class, () -> service.save(naturezaJuridica));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Descri????o j?? cadastrada"));
    }

    // @Test
    @Order(4)
    void test_buscaPorDescricao() {
	List<NaturezaJuridica> list = this.service.buscaPorDescricao("M");

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));
    }

    // @Test
    @Order(5)
    void test_buscaPorDescricao_nao_encontrado() {
	List<NaturezaJuridica> list = this.service.buscaPorDescricao("fdasdfdasdf");

	MatcherAssert.assertThat(list.size(), Matchers.is(0));
    }

    // @Test
    @Order(6)
    void test_findById() {
	ids.forEach(id -> {
	    NaturezaJuridica naturezaJuridica = service.findById(id);
	    assertNotNull(naturezaJuridica);
	});
    }

    // @Test
    @Order(7)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(NaturezaJuridicaIdNotFoundException.class, () -> this.service.findById(15446));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Natureza Jur??dica n??o encontrada"));
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
