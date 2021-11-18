package com.xfatur.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

import com.xfatur.exception.DestinatarioCNPJCPFExistException;
import com.xfatur.exception.DestinatarioIdNotFoundException;
import com.xfatur.model.Destinatario;
import com.xfatur.model.Entrega;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;
import com.xfatur.model.Retirada;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class DestinatarioServiceTest {
    Stream<Destinatario> model() {
	return Stream.of(CreateModelTest.createDestinatarioPJ(), CreateModelTest.createDestinatarioPJI(), CreateModelTest.createDestinatarioPF());
    }

    Stream<Entrega> modelEntrega() {
	return Stream.of(CreateModelTest.createEntrega1());
    }

    Stream<Retirada> modelRetirada() {
	return Stream.of(CreateModelTest.createRetirada1(), CreateModelTest.createRetirada2());
    }

    @Autowired
    DestinatarioService destinatarioService;
    @Autowired
    RamoAtividadeService ramoAtividadeService;
    @Autowired
    NaturezaJuridicaService naturezaJuridicaService;
    @Autowired
    RepresentanteService representanteService;

    @Autowired
    EntregaService entregaService;

    @Autowired
    RetiradaService retiradaService;

    List<Integer> idsRepresentante = new ArrayList<Integer>();
    List<Integer> idsRamoAtividade = new ArrayList<Integer>();
    List<Integer> idsNaturezaJuridica = new ArrayList<Integer>();
    List<Integer> idsDestinatario = new ArrayList<Integer>();

    @BeforeAll
    void cadastrosAuxiliares() {
	CreateModelTest.ramoAtividadeList().forEach(ra -> {
	    RamoAtividade saved = ramoAtividadeService.save(ra);
	    idsRamoAtividade.add(saved.getId());
	}

	);
	CreateModelTest.naturezaJuridicaList().forEach(nj -> {
	    NaturezaJuridica saved = naturezaJuridicaService.save(nj);
	    idsNaturezaJuridica.add(saved.getId());
	});

	CreateModelTest.representanteList().forEach(r -> {
	    Representante saved = representanteService.save(r);
	    idsRepresentante.add(saved.getId());
	});
    }

    @AfterAll
    void deletarCadastrosAuxiliares() {
	idsDestinatario.forEach(id -> destinatarioService.deleteById(id));
	idsRamoAtividade.forEach(id -> ramoAtividadeService.delete(id));
	idsNaturezaJuridica.forEach(id -> naturezaJuridicaService.delete(id));
	idsRepresentante.forEach(id -> representanteService.delete(id));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void teste_save(Destinatario destinatario) {
	int ramoAtividade_id = CreateModelTest.getCodigoAleatorio(idsRamoAtividade);
	int naturezaJuridica_id = CreateModelTest.getCodigoAleatorio(idsNaturezaJuridica);
	int representante_id = CreateModelTest.getCodigoAleatorio(idsRepresentante);

	RamoAtividade ramoAtividade = ramoAtividadeService.findById(ramoAtividade_id);
	NaturezaJuridica naturezaJuridica = naturezaJuridicaService.findById(naturezaJuridica_id);
	Representante representante = representanteService.findById(representante_id);

	destinatario.setRamoAtividade(ramoAtividade);
	destinatario.setNaturezaJuridica(naturezaJuridica);
	destinatario.setRepresentante(representante);

	Destinatario saved = destinatarioService.save(destinatario);

	idsDestinatario.add(saved.getId());
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
    void test_save_cnpjcpf_ja_cadastrado_exception(Destinatario destinatario) {
	Exception exception = Assertions.assertThrows(DestinatarioCNPJCPFExistException.class, () -> {
	    int ramoAtividade_id = CreateModelTest.getCodigoAleatorio(idsRamoAtividade);
	    int naturezaJuridica_id = CreateModelTest.getCodigoAleatorio(idsNaturezaJuridica);
	    int representante_id = CreateModelTest.getCodigoAleatorio(idsRepresentante);

	    RamoAtividade ramoAtividade = ramoAtividadeService.findById(ramoAtividade_id);
	    NaturezaJuridica naturezaJuridica = naturezaJuridicaService.findById(naturezaJuridica_id);
	    Representante representante = representanteService.findById(representante_id);

	    destinatario.setRamoAtividade(ramoAtividade);
	    destinatario.setNaturezaJuridica(naturezaJuridica);
	    destinatario.setRepresentante(representante);

	    destinatarioService.save(destinatario);
	}

	);

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    @Test
    @Order(3)
    void test_busca_cnpjcpf_nao_encontrado_exception() {
	Destinatario destinatario = destinatarioService.buscaPorCNPJCPF("456");

	assertNull(destinatario);
    }

    @Test
    @Order(4)
    void test_busca_cnpjcpf() {
	Destinatario destinatario = destinatarioService.buscaPorCNPJCPF("60977980000109");

	assertNotNull(destinatario);
    }

//    @ParameterizedTest
//    @MethodSource("modelEntrega")
//    @Order(5)
//    void test_gravar_entrega(Entrega entrega) {
//	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(0));
//
//	entrega.setDestinatario(destinatario);
//	entrega.setId(destinatario.getId());
//
//	Entrega saved = entregaService.save(entrega);
//
//	assertNotNull(saved);
//    }
//
//    @ParameterizedTest
//    @MethodSource("modelRetirada")
//    @Order(6)
//    void test_gravar_retirada(Retirada retirada) {
//	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));
//
//	retirada.setDestinatario(destinatario);
//	retirada.setId(destinatario.getId());
//
//	Retirada saved = retiradaService.save(retirada);
//	assertNotNull(saved);
//
//    }

    @Test
    @Order(7)
    void test_buscaPorNome() {
	List<Destinatario> destinatarios = destinatarioService.buscaPorNome("a");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(8)
    void test_buscaPorNome_tamanho_0() {
	List<Destinatario> destinatarios = destinatarioService.buscaPorNome("aaaaaaaaaaaaaaaa");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.is(0));
    }

    @Test
    @Order(9)
    void test_findById() {
	idsDestinatario.forEach(id -> {
	    Destinatario destinatario = destinatarioService.findById(id);
	    assertNotNull(destinatario);
	});

    }

    @Test
    @Order(10)
    void test_findById_NotFoundException() {
	Exception exception = Assertions.assertThrows(DestinatarioIdNotFoundException.class, () -> destinatarioService.findById(10001));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Destinatario não encontrado"));
    }

    @Test
    @Order(11)
    void test_find_buscaPorIdComEntrega() {
	idsDestinatario.forEach(id -> {
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>222:" + id);

	    Destinatario destinatario = destinatarioService.findById(id);
	    assertNotNull(destinatario);
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>222:" + id);
	});

    }
}