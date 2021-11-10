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
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.exception.RamoAtividadeNotFoundException;
import com.xfatur.model.RamoAtividade;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class RamoAtividadeServiceTest {

    @Autowired
    private RamoAtividadeService ramoAtividadeService;

    private List<RamoAtividadeDTO> ramoAtividadeDTOList;

    @BeforeAll
    public void createRamoAtividadeService() {
	List<RamoAtividade> ramoAtividadeList = new ArrayList<RamoAtividade>();
	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("ATACADISTA");
	ramoAtividadeList.add(ramoAtividade);

	ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("BAR");
	ramoAtividadeList.add(ramoAtividade);

	ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("BAR/RESTAURANTE");
	ramoAtividadeList.add(ramoAtividade);

	ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("BUFFET");
	ramoAtividadeList.add(ramoAtividade);

	ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("CONSUMIDOR");
	ramoAtividadeList.add(ramoAtividade);

	ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("DIRETO");
	ramoAtividadeList.add(ramoAtividade);
	ramoAtividadeDTOList = ramoAtividadeList.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    @AfterAll
    public void delete() {
	for (RamoAtividadeDTO ramoAtividadeDTO : ramoAtividadeDTOList) {
	    this.ramoAtividadeService.delete(ramoAtividadeDTO.getId());
	}

	Boolean result = this.ramoAtividadeService.delete(454);

	MatcherAssert.assertThat(result, Matchers.equalToObject(Boolean.FALSE));

    }

    @Test
    @Order(1)
    public void test_save() {

	for (int i = 0; i < ramoAtividadeDTOList.size(); i++) {
	    RamoAtividadeDTO ramoAtividadeDTO = ramoAtividadeDTOList.get(i);

	    RamoAtividadeDTO raDTO = this.ramoAtividadeService.save(ramoAtividadeDTO);

	    ramoAtividadeDTO.setId(raDTO.getId());
	    MatcherAssert.assertThat(raDTO.getId(), Matchers.equalTo(ramoAtividadeDTO.getId()));

	}

    }

    @Test
    @Order(2)
    public void test_queryByDescricao() {
	String descricao = "M";
	List<RamoAtividadeDTO> list = this.ramoAtividadeService.queryByDescricao(descricao);
	MatcherAssert.assertThat(list.size(), Matchers.greaterThan(0));

    }

    @Test
    @Order(3)
    public void test_findById() {
	RamoAtividadeDTO toFind = this.ramoAtividadeDTOList.get(0);
	RamoAtividadeDTO found = this.ramoAtividadeService.findById(toFind.getId());
	RamoAtividade ramoAtividade = RamoAtividade.convert(found);

	MatcherAssert.assertThat(ramoAtividade.getId(), Matchers.equalToObject(found.getId()));

	Exception exception = Assertions.assertThrows(RamoAtividadeNotFoundException.class, () -> {
	    this.ramoAtividadeService.findById(100);
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Ramo de Atividade não encontrado."));

    }

}
