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

import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.exception.RepresentanteException;
import com.xfatur.exception.RepresentanteNotFoundException;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class RepresentanteServiceTest {

    @Autowired
    RepresentanteService service;

    static Stream<RepresentanteDTO> model() {
	return Stream.of(CreateModelTest.createRepresentante1(), CreateModelTest.createRepresentante2());
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(RepresentanteDTO r) {
	Integer id = service.save(r).getId();
	r.setId(id);

	MatcherAssert.assertThat(id, Matchers.greaterThan(0));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
    void test_save_cnpjcpf_ja_cadastrado(RepresentanteDTO r) {
	RepresentanteException exception = Assertions.assertThrows(RepresentanteException.class, () -> service.save(r));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    @Test
    @Order(3)
    void test_findByCNPJCPF_encontrado() {
	String cnpjcpf = this.service.findByCNPJCPF("77851609000107").getCNPJCPF();

	MatcherAssert.assertThat(cnpjcpf, Matchers.is("77851609000107"));
    }

    @Test
    @Order(4)
    void test_findByCNPJCPF_naoEncontrado() {
	Exception exception = Assertions.assertThrows(RepresentanteNotFoundException.class, () -> this.service.findByCNPJCPF("12456"));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Representante não encontrado"));
    }

    @Test
    @Order(5)
    void test_buscaPorNome() {
	List<RepresentanteDTO> found = this.service.buscaPorNome("S");

	MatcherAssert.assertThat(found.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(6)
    void test_buscaPorNome_total_2() {
	List<RepresentanteDTO> found = this.service.buscaPorNome("S");

	MatcherAssert.assertThat(found.size(), Matchers.is(2));
    }

    @Test
    @Order(7)
    void test_buscaPorNome_nao_encontrado() {
	List<RepresentanteDTO> found = this.service.buscaPorNome("sss");

	MatcherAssert.assertThat(found.size(), Matchers.is(0));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(8)
    void test_delete(RepresentanteDTO r) {
	Integer id = service.findByCNPJCPF(r.getCNPJCPF()).getId();
	Boolean apagado = service.delete(id);

	MatcherAssert.assertThat(apagado, Matchers.is(TRUE));
    }

    @Test
    @Order(9)
    void test_delete_nao_encontrado() {
	Boolean apagado = this.service.delete(100000);

	MatcherAssert.assertThat(apagado, Matchers.is(FALSE));
    }
}
