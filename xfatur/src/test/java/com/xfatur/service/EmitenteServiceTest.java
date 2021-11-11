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

import com.xfatur.dto.EmitenteDTO;
import com.xfatur.exception.EmitenteException;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class EmitenteServiceTest {

    @Autowired
    EmitenteService service;

    static Stream<EmitenteDTO> model() {
	return Stream.of(CreateModelTest.createEmitente1());
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(EmitenteDTO e) {
	Integer id = this.service.save(e).getId();

	MatcherAssert.assertThat(id, Matchers.greaterThan(0));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
    void test_save_cnpj_ja_cadastrado(EmitenteDTO e) {
	EmitenteException exception = Assertions.assertThrows(EmitenteException.class, () -> service.save(e));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(3)
    void test_findById(EmitenteDTO e) {
	Integer id = service.findByCNPJ(e.getCNPJ()).getId();
	Integer id_encontrado = service.findById(id).getId();

	MatcherAssert.assertThat(id, Matchers.is(id_encontrado));
    }

    @Test
    @Order(4)
    void test_findById_erro() {
	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> this.service.findById(10000));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Emitente Não encontrado"));
    }

    @Test
    @Order(6)
    void test_findByCNPJ() {
	EmitenteDTO found = this.service.findByCNPJ("65037603000103");

	MatcherAssert.assertThat(found.getCNPJ(), Matchers.is("65037603000103"));
    }

    @Test
    @Order(7)
    void test_findByCNPJ_erro() {
	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> this.service.findByCNPJ(""));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Emitente Não encontrado"));
    }

    @Test
    @Order(8)
    void test_buscaPorNome() {
	List<EmitenteDTO> emitentes = this.service.buscaPorNome("Empresa");

	MatcherAssert.assertThat(emitentes.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(9)
    void test_buscaPorNome_tamanho_0() {
	List<EmitenteDTO> emitentes = this.service.buscaPorNome("fdasfdsa");

	MatcherAssert.assertThat(emitentes.size(), Matchers.is(0));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(10)
    void delete(EmitenteDTO e) {
	Integer id = service.findByCNPJ(e.getCNPJ()).getId();

	Boolean result = service.delete(id);

	MatcherAssert.assertThat(result, Matchers.is(TRUE));
    }

    @Test
    @Order(11)
    void delete_error() {
	Boolean result = service.delete(77);

	MatcherAssert.assertThat(result, Matchers.is(FALSE));
    }
}