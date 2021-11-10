package com.xfatur.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.exception.NaturezaJuridicaNotFoundException;
import com.xfatur.model.NaturezaJuridica;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class NaturezaJuridicaServiceTest {

    @Autowired
    private NaturezaJuridicaService naturezaJuridicaService;

    private List<NaturezaJuridicaDTO> naturezaJuridicaDTOList;

    @BeforeAll
    public void createNaturezaJuridicaService() {
	List<NaturezaJuridica> naturezaJuridicaList = new ArrayList<NaturezaJuridica>();
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("EMPRESA PEQUENO PORTE (EPP)");
	naturezaJuridicaList.add(naturezaJuridica);

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("MICRO EMPRESA (ME)");
	naturezaJuridicaList.add(naturezaJuridica);

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("EMPRESA (OUTRAS)");
	naturezaJuridicaList.add(naturezaJuridica);

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("CONSUMIDOR FINAL");
	naturezaJuridicaList.add(naturezaJuridica);

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("ENQUADRADO NO SIMPLES NACIONAL");
	naturezaJuridicaList.add(naturezaJuridica);

	naturezaJuridicaDTOList = naturezaJuridicaList.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    @AfterAll
    public void delete() {
	for (NaturezaJuridicaDTO naturezaJuridicaDTO : naturezaJuridicaDTOList) {
	    this.naturezaJuridicaService.delete(naturezaJuridicaDTO.getId());
	}

	Boolean result = this.naturezaJuridicaService.delete(454);

	MatcherAssert.assertThat(result, Matchers.equalToObject(Boolean.FALSE));

    }

    @Test
    @Order(1)
    public void test_save() {

	for (int i = 0; i < naturezaJuridicaDTOList.size(); i++) {
	    NaturezaJuridicaDTO naturezaJuridicaDTO = naturezaJuridicaDTOList.get(i);

	    NaturezaJuridicaDTO raDTO = this.naturezaJuridicaService.save(naturezaJuridicaDTO);

	    naturezaJuridicaDTO.setId(raDTO.getId());
	    MatcherAssert.assertThat(raDTO.getId(), Matchers.equalTo(naturezaJuridicaDTO.getId()));

	}

    }

    @Test
    @Order(2)
    public void test_queryByDescricao() {
	List<NaturezaJuridicaDTO> list = this.naturezaJuridicaService.queryByDescricao("M");
	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));

	list = this.naturezaJuridicaService.queryByDescricao("fdasdfdasdf");
	MatcherAssert.assertThat(list.size(), Matchers.equalTo(0));

    }

    @Test
    @Order(3)
    public void test_findById() {
	NaturezaJuridicaDTO toFind = this.naturezaJuridicaDTOList.get(0);
	NaturezaJuridicaDTO found = this.naturezaJuridicaService.findById(toFind.getId());
	NaturezaJuridica naturezaJuridica = NaturezaJuridica.convert(found);

	MatcherAssert.assertThat(naturezaJuridica.getId(), Matchers.equalToObject(found.getId()));

	Exception exception = Assertions.assertThrows(NaturezaJuridicaNotFoundException.class, () -> {
	    this.naturezaJuridicaService.findById(100);
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Natureza Jurídica não encontrada"));

    }

}
