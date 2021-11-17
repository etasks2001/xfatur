package com.xfatur.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

import com.xfatur.exception.RepresentanteException;
import com.xfatur.exception.RepresentanteIdNotFoundException;
import com.xfatur.exception.RepresentanteNotFoundException;
import com.xfatur.model.Representante;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class RepresentanteServiceTest {

    @Autowired
    RepresentanteService service;

    List<Integer> ids = new ArrayList<Integer>();

    static Stream<Representante> model() {
	return Stream.of(CreateModelTest.createRepresentante1(), CreateModelTest.createRepresentante2());
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void test_save(Representante r) {
	Representante representante = service.save(r);
	ids.add(representante.getId());
    }

    @Test
    @Order(2)
    void test_update() {
	ids.forEach(id -> {
	    Representante representante = service.findById(id);
	    representante.setxNome(representante.getxNome() + " alterado");

	    service.save(representante);

	});
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(3)
    void test_save_cnpjcpf_ja_cadastrado(Representante r) {
	RepresentanteException exception = Assertions.assertThrows(RepresentanteException.class, () -> service.save(r));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    @Test
    @Order(4)
    void test_findByCNPJCPF_encontrado() {
	String cnpjcpf = this.service.findByCNPJCPF("77851609000107").getCNPJCPF();

	MatcherAssert.assertThat(cnpjcpf, Matchers.is("77851609000107"));
    }

    @Test
    @Order(5)
    void test_findByCNPJCPF_nao_encontrado() {
	Exception exception = Assertions.assertThrows(RepresentanteNotFoundException.class, () -> this.service.findByCNPJCPF("12456"));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Representante não encontrado"));
    }

    @Test
    @Order(6)
    void test_findById_nao_encontrado() {
	Exception exception = Assertions.assertThrows(RepresentanteIdNotFoundException.class, () -> this.service.findById(484567489));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Código do Representante não encontrado"));

    }

    @Test
    @Order(7)
    void test_buscaPorNome() {
	List<Representante> found = this.service.buscaPorNome("S");

	MatcherAssert.assertThat(found.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(8)
    void test_buscaPorNome_nao_encontrado() {
	List<Representante> found = this.service.buscaPorNome("sss");

	MatcherAssert.assertThat(found.size(), Matchers.is(0));
    }

    @Test
    @Order(9)
    void test_delete() {
	ids.forEach(id -> {
	    Boolean apagado = service.delete(id);

	    assertEquals(apagado, TRUE);
	});
    }

    @Test
    @Order(10)
    void test_delete_nao_encontrado() {
	Boolean apagado = this.service.delete(100000);

	assertEquals(apagado, FALSE);
    }
}
