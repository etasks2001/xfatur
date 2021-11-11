package com.xfatur.service;

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

import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.exception.NaturezaJuridicaException;
import com.xfatur.exception.NaturezaJuridicaNotFoundException;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class NaturezaJuridicaServiceTest {
    @Autowired
    NaturezaJuridicaService service;

    static Stream<NaturezaJuridicaDTO> model() {
	return Stream.of(CreateModelTest.createNaturezaJuridica1(), CreateModelTest.createNaturezaJuridica2(), CreateModelTest.createNaturezaJuridica3(), CreateModelTest.createNaturezaJuridica4(),
		CreateModelTest.createNaturezaJuridica5());

    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(NaturezaJuridicaDTO nj) {
	Integer id = this.service.save(nj).getId();
	nj.setId(id);

	MatcherAssert.assertThat(id, Matchers.is(nj.getId()));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
    void test_save_cnpj_ja_cadastrado(NaturezaJuridicaDTO e) {
	NaturezaJuridicaException exception = Assertions.assertThrows(NaturezaJuridicaException.class, () -> service.save(e));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Descrição já cadastrada"));
    }

    @Test
    @Order(3)
    void test_buscaPorDescricao() {
	List<NaturezaJuridicaDTO> list = this.service.buscaPorDescricao("M");

	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    void test_buscaPorDescricao_tamanho_0() {
	List<NaturezaJuridicaDTO> list = this.service.buscaPorDescricao("fdasdfdasdf");

	MatcherAssert.assertThat(list.size(), Matchers.is(0));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(5)
    void test_findById(NaturezaJuridicaDTO nj) {
	List<NaturezaJuridicaDTO> findAll = service.findAll();

	Integer id = findAll.get(0).getId();

	Integer id2 = this.service.findById(id).getId();

	MatcherAssert.assertThat(id2, Matchers.is(id));
    }

    @Test
    @Order(6)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(NaturezaJuridicaNotFoundException.class, () -> this.service.findById(100));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Natureza Jurídica não encontrada"));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(7)
    void delete(NaturezaJuridicaDTO n) {
	List<NaturezaJuridicaDTO> findAll = service.findAll();
	findAll.forEach(nj -> {
	    Boolean apagado = this.service.delete(nj.getId());
	    MatcherAssert.assertThat(apagado, Matchers.is(TRUE));
	});
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(8)
    void delete_nao_encontrado(NaturezaJuridicaDTO n) {
	Boolean result = this.service.delete(454);

	MatcherAssert.assertThat(result, Matchers.is(Boolean.FALSE));
    }

}
