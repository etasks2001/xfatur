package com.xfatur.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
		return Stream.of(CreateModelTest.createRamoAtividade1(), CreateModelTest.createRamoAtividade2(), CreateModelTest.createRamoAtividade3(), CreateModelTest.createRamoAtividade4(), CreateModelTest.createRamoAtividade5(), CreateModelTest.createRamoAtividade6());
	}

	Stream<NaturezaJuridicaDTO> naturezaJuridicaList() {
		return Stream.of(CreateModelTest.createNaturezaJuridica1(), CreateModelTest.createNaturezaJuridica2(), CreateModelTest.createNaturezaJuridica3(), CreateModelTest.createNaturezaJuridica4(), CreateModelTest.createNaturezaJuridica5());
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

	List<Integer> codigosRepresentante = new ArrayList<Integer>();
	List<Integer> codigosRamoAtividade = new ArrayList<Integer>();
	List<Integer> codigosNaturezaJuridica = new ArrayList<Integer>();
	List<Integer> codigosDestinatario = new ArrayList<Integer>();

	@BeforeAll
	void cadastrosAuxiliares() {
		ramoAtividadeList().forEach(a -> {

			RamoAtividadeDTO saved = ramoAtividadeService.save(a);
			codigosRamoAtividade.add(saved.getId());
		}

		);
		naturezaJuridicaList().forEach(a -> {

			NaturezaJuridicaDTO saved = naturezaJuridicaService.save(a);

			codigosNaturezaJuridica.add(saved.getId());

		});
		representanteList().forEach(a -> {

			RepresentanteDTO saved = representanteService.save(a);

			codigosRepresentante.add(saved.getId());

		}

		);
	}

	@AfterAll
	void deletarCadastrosAuxiliares() {
		codigosDestinatario.forEach(id -> destinatarioService.deleteById(id));
		codigosRepresentante.forEach(id -> representanteService.delete(id));
		codigosNaturezaJuridica.forEach(id -> naturezaJuridicaService.delete(id));
		codigosRamoAtividade.forEach(id -> ramoAtividadeService.delete(id));
	}

	Stream<DestinatarioDTO> model() {
		return Stream.of(CreateModelTest.createPJ());
	}

	@ParameterizedTest
	@MethodSource("model")
	@Order(1)
	void teste_save(DestinatarioDTO d) {

		int representante_id = getCodigoAleatorio(codigosRepresentante);
		int naturezaJuridica_id = getCodigoAleatorio(codigosNaturezaJuridica);
		int ramoAtividade_id = getCodigoAleatorio(codigosRamoAtividade);

		d.setRepresentante_id(representante_id);
		d.setNaturezaJuridica_id(naturezaJuridica_id);
		d.setRamoAtividade_id(ramoAtividade_id);

		DestinatarioDTO saved = destinatarioService.save(d);

		codigosDestinatario.add(saved.getId());

		MatcherAssert.assertThat(saved.getId(), Matchers.greaterThan(0));
	}

	Integer getCodigoAleatorio(List<Integer> lista) {
		Random rand = new Random();
		int index = rand.nextInt((lista.size() - 0) + 1) + 0;
		index = index >= lista.size() ? index = lista.size() - 1 : index;
		return lista.get(index);

	}

}
