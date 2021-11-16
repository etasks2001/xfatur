package com.xfatur.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.xfatur.dto.EntregaDTO;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.dto.RetiradaDTO;
import com.xfatur.exception.DestinatarioCNPJCPFNotFoundException;
import com.xfatur.exception.DestinatarioIdNotFoundException;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class DestinatarioServiceTest {
    Stream<DestinatarioDTO> model() {
	return Stream.of(CreateModelTest.createDestinatarioPJ(), CreateModelTest.createDestinatarioPJI(), CreateModelTest.createDestinatarioPF());
    }

    Stream<EntregaDTO> modelEntrega() {
	return Stream.of(CreateModelTest.createEntrega1());
    }

    Stream<RetiradaDTO> modelRetirada() {
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
	    RamoAtividadeDTO saved = ramoAtividadeService.save(ra);
	    idsRamoAtividade.add(saved.getId());
	}

	);
	CreateModelTest.naturezaJuridicaList().forEach(nj -> {
	    NaturezaJuridicaDTO saved = naturezaJuridicaService.save(nj);
	    idsNaturezaJuridica.add(saved.getId());
	});

	CreateModelTest.representanteList().forEach(r -> {
	    RepresentanteDTO saved = representanteService.save(r);
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
    void teste_save(DestinatarioDTO destinatarioDTO) {
	int ramoAtividade_id = CreateModelTest.getCodigoAleatorio(idsRamoAtividade);
	int naturezaJuridica_id = CreateModelTest.getCodigoAleatorio(idsNaturezaJuridica);
	int representante_id = CreateModelTest.getCodigoAleatorio(idsRepresentante);

	destinatarioDTO.setRamoAtividade_id(ramoAtividade_id);
	destinatarioDTO.setNaturezaJuridica_id(naturezaJuridica_id);
	destinatarioDTO.setRepresentante_id(representante_id);

	DestinatarioDTO saved = destinatarioService.save(destinatarioDTO);

	idsDestinatario.add(saved.getId());
    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(2)
//    void test_save_cnpjcpf_ja_cadastrado_exception(DestinatarioDTO destinatarioDTO) {
//	Exception exception = Assertions.assertThrows(DestinatarioCNPJCPFExistException.class, () -> destinatarioService.save(destinatarioDTO));
//
//	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
//    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(3)
    void test_busca_cnpjcpf_nao_encontrado_exception(DestinatarioDTO destinatarioDTO) {
	Exception exception = Assertions.assertThrows(DestinatarioCNPJCPFNotFoundException.class, () -> destinatarioService.buscaPorCNPJCPF("456"));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF não encontrado"));
    }

    @Test
    @Order(4)
    void test_busca_cnpjcpf() {
	DestinatarioDTO destinatarioDTO = destinatarioService.buscaPorCNPJCPF("60977980000109");

	assertNotNull(destinatarioDTO);
    }

    @ParameterizedTest
    @MethodSource("modelEntrega")
    @Order(5)
    void test_gravar_entrega(EntregaDTO entregaDTO) {
	DestinatarioDTO destinatarioDTO = destinatarioService.findById(idsDestinatario.get(0));

	entregaDTO.setDestinatarioDTO(destinatarioDTO);
	entregaDTO.setId(destinatarioDTO.getId());

	EntregaDTO saved = entregaService.save(entregaDTO);

	assertNotNull(saved);
    }

    @ParameterizedTest
    @MethodSource("modelRetirada")
    @Order(6)
    void test_gravar_retirada(RetiradaDTO retiradaDTO) {
	DestinatarioDTO destinatarioDTO = destinatarioService.findById(idsDestinatario.get(1));

	retiradaDTO.setDestinatarioDTO(destinatarioDTO);
	retiradaDTO.setId(destinatarioDTO.getId());

	RetiradaDTO saved = retiradaService.save(retiradaDTO);
	assertNotNull(saved);

    }

    @Test
    @Order(7)
    void test_buscaPorNome() {
	List<DestinatarioDTO> destinatarios = destinatarioService.buscaPorNome("a");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(8)
    void test_buscaPorNome_tamanho_0() {
	List<DestinatarioDTO> destinatarios = destinatarioService.buscaPorNome("aaaaaaaaaaaaaaaa");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.is(0));
    }

    @Test
    @Order(9)
    void test_findById() {
	idsDestinatario.forEach(id -> {
	    DestinatarioDTO destinatarioDTO = destinatarioService.findById(id);
	    assertNotNull(destinatarioDTO);
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

	    DestinatarioDTO destinatarioDTO = destinatarioService.findById(id);
	    assertNotNull(destinatarioDTO);
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>222:" + id);
	});

    }

}
