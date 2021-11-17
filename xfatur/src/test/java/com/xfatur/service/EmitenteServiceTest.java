package com.xfatur.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.xfatur.exception.EmitenteException;
import com.xfatur.exception.EmitenteIdNotFoundException;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.model.Emitente;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class EmitenteServiceTest {

    @Autowired
    EmitenteService service;

    private static Integer id;

    static Stream<Emitente> model() {
	return Stream.of(CreateModelTest.createEmitente1());
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(Emitente e) {
	id = service.save(e).getId();
    }

    @Test
    @Order(2)
    void test_findById() {
	Emitente emitente = service.findById(id);
	assertNotNull(emitente);
	assertEquals(CreateModelTest.createEmitente1().getxNome(), emitente.getxNome());

	Exception exception = Assertions.assertThrows(EmitenteIdNotFoundException.class, () -> service.findById(1456));
	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Emitente Não encontrado"));
    }

    @Test
    @Order(3)
    void test_update() {
	Emitente emitente = service.findById(id);
	emitente.setxNome("Nova empresa");

	service.save(emitente);
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(4)
    void test_save_cnpj_ja_cadastrado(Emitente e) {
	EmitenteException exception = Assertions.assertThrows(EmitenteException.class, () -> service.save(e));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    @Test
    @Order(5)
    void test_findByCNPJ() {
	Emitente emitente = service.findByCNPJ("65037603000103");

	assertNotNull(emitente);
    }

    @Test
    @Order(6)
    void test_findByCNPJ_erro() {
	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> service.findByCNPJ(""));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Emitente Não encontrado"));
    }

    @Test
    @Order(7)
    void test_buscaPorNome() {
	List<Emitente> emitentes = service.buscaPorNome("mpresa");

	MatcherAssert.assertThat(emitentes.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(8)
    void test_buscaPorNome_nao_encontrado() {
	List<Emitente> emitentes = service.buscaPorNome("aaaaaaaaaaaa");

	MatcherAssert.assertThat(emitentes.size(), Matchers.is(0));
    }

    @Test
    @Order(9)
    void test_delete() {
	Boolean result = service.delete(id);

	MatcherAssert.assertThat(result, Matchers.is(TRUE));
    }

    @Test
    @Order(10)
    void test_delete_error() {
	Boolean result = service.delete(77);

	MatcherAssert.assertThat(result, Matchers.is(FALSE));
    }
}