package com.xfatur.service;

import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class DestinatarioServiceTest {

    Stream<RamoAtividadeDTO> ramoAtividadeList() {
	return Stream.of(CreateModelTest.createRamoAtividade1(), CreateModelTest.createRamoAtividade2(), CreateModelTest.createRamoAtividade3(), CreateModelTest.createRamoAtividade4(),
		CreateModelTest.createRamoAtividade5(), CreateModelTest.createRamoAtividade6());
    }

    Stream<NaturezaJuridicaDTO> naturezaJuridicaList() {
	return Stream.of(CreateModelTest.createNaturezaJuridica1(), CreateModelTest.createNaturezaJuridica2(), CreateModelTest.createNaturezaJuridica3(), CreateModelTest.createNaturezaJuridica4(),
		CreateModelTest.createNaturezaJuridica5());
    }

    Stream<RepresentanteDTO> representanteList() {
	return Stream.of(CreateModelTest.createRepresentante1(), CreateModelTest.createRepresentante2());
    }

    @Autowired
    DestinatarioService destinatarioService;
    @Autowired
    RamoAtividadeService ramoAtividadeService;
    @Autowired
    RepresentanteService representanteService;
    @Autowired
    NaturezaJuridicaService naturezaJuridicaService;

    @BeforeAll
    void cadastrosAuxiliares() {
	ramoAtividadeList().forEach(a -> ramoAtividadeService.save(a));
	naturezaJuridicaList().forEach(a -> naturezaJuridicaService.save(a));
	representanteList().forEach(a -> representanteService.save(a));

    }

    @AfterAll
    void deletarCadastrosAuxiliares() {
	List<RamoAtividadeDTO> listRA = ramoAtividadeService.findAll();
	listRA.forEach(f -> ramoAtividadeService.delete(f.getId()));

	List<NaturezaJuridicaDTO> listNJ = naturezaJuridicaService.findAll();
	listNJ.forEach(c -> naturezaJuridicaService.delete(c.getId()));

	List<RepresentanteDTO> listR = representanteService.buscaPorNome("");
	listR.forEach(c -> representanteService.delete(c.getId()));

    }

    Stream<DestinatarioDTO> model() {
	return Stream.of(CreateModelTest.createPJ());
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
    void teste_save(DestinatarioDTO d) {

	RamoAtividadeDTO createRamoAtividade1 = ramoAtividadeService.findAll().get(0);
	NaturezaJuridicaDTO createNaturezaJuridica1 = naturezaJuridicaService.findAll().get(0);
	RepresentanteDTO createRepresentante1 = representanteService.buscaPorNome("").get(0);

	d.setRamoAtividade(ModelConverter.convert(createRamoAtividade1));
	d.setRepresentante(ModelConverter.convert(createRepresentante1));
	d.setNaturezaJuridica(ModelConverter.convert(createNaturezaJuridica1));

	DestinatarioDTO saved = destinatarioService.save(d);

	MatcherAssert.assertThat(saved.getId(), Matchers.greaterThan(0));
    }

}
