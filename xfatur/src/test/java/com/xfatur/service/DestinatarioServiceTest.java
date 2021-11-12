package com.xfatur.service;

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

import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.exception.DestinatarioException;
import com.xfatur.exception.DestinatarioIdNotFoundException;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class DestinatarioServiceTest {
    Stream<DestinatarioDTO> model() {
	return Stream.of(CreateModelTest.createPJ());
    }

    @Autowired
    DestinatarioService destinatarioService;
    @Autowired
    RamoAtividadeService ramoAtividadeService;
    @Autowired
    NaturezaJuridicaService naturezaJuridicaService;
    @Autowired
    RepresentanteService representanteService;

    List<Integer> codigosRepresentante = new ArrayList<Integer>();
    List<Integer> codigosRamoAtividade = new ArrayList<Integer>();
    List<Integer> codigosNaturezaJuridica = new ArrayList<Integer>();
    List<Integer> codigosDestinatario = new ArrayList<Integer>();

    @BeforeAll
    void cadastrosAuxiliares() {
	CreateModelTest.ramoAtividadeList().forEach(a -> {
	    RamoAtividadeDTO saved = ramoAtividadeService.save(a);
	    codigosRamoAtividade.add(saved.getId());
	}

	);
	CreateModelTest.naturezaJuridicaList().forEach(a -> {
	    NaturezaJuridicaDTO saved = naturezaJuridicaService.save(a);
	    codigosNaturezaJuridica.add(saved.getId());
	});

	CreateModelTest.representanteList().forEach(a -> {
	    RepresentanteDTO saved = representanteService.save(a);
	    codigosRepresentante.add(saved.getId());
	});
    }

    @AfterAll
    void deletarCadastrosAuxiliares() {
	codigosDestinatario.forEach(id -> destinatarioService.deleteById(id));
	codigosRamoAtividade.forEach(id -> ramoAtividadeService.delete(id));
	codigosNaturezaJuridica.forEach(id -> naturezaJuridicaService.delete(id));
	codigosRepresentante.forEach(id -> representanteService.delete(id));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void teste_save(DestinatarioDTO destinatarioDTO) {
	int ramoAtividade_id = CreateModelTest.getCodigoAleatorio(codigosRamoAtividade);
	int naturezaJuridica_id = CreateModelTest.getCodigoAleatorio(codigosNaturezaJuridica);
	int representante_id = CreateModelTest.getCodigoAleatorio(codigosRepresentante);

	destinatarioDTO.setRamoAtividade_id(ramoAtividade_id);
	destinatarioDTO.setNaturezaJuridica_id(naturezaJuridica_id);
	destinatarioDTO.setRepresentante_id(representante_id);

	DestinatarioDTO saved = destinatarioService.save(destinatarioDTO);

	codigosDestinatario.add(saved.getId());

	MatcherAssert.assertThat(saved.getId(), Matchers.greaterThan(0));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
    void test_save_cnpjcpf_ja_cadastrado_exception(DestinatarioDTO destinatarioDTO) {
	Exception exception = Assertions.assertThrows(DestinatarioException.class, () -> destinatarioService.save(destinatarioDTO));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    @Test
    @Order(3)
    void test_buscaPorNome() {
	List<DestinatarioDTO> destinatarios = destinatarioService.buscaPorNome("a");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    void test_buscaPorNome_tamanho_0() {
	List<DestinatarioDTO> destinatarios = destinatarioService.buscaPorNome("aaaaaaaaaaaaaaaa");
	MatcherAssert.assertThat(destinatarios.size(), Matchers.is(0));
    }

    @Test
    @Order(5)
    void test_findById() {
	List<DestinatarioDTO> destinatarios = destinatarioService.buscaPorNome("a");
	Integer id = destinatarios.get(0).getId();

	DestinatarioDTO found = destinatarioService.findById(id);

	MatcherAssert.assertThat(found, Matchers.notNullValue());
    }

    @Test
    @Order(6)
    void test_findById_NotFoundException() {
	Exception exception = Assertions.assertThrows(DestinatarioIdNotFoundException.class, () -> destinatarioService.findById(10001));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Destinatario não encontrado"));
    }
}
