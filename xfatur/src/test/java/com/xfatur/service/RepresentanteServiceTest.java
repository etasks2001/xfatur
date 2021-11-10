package com.xfatur.service;

import java.util.List;

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
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.exception.RepresentanteNotFoundException;
import com.xfatur.model.Endereco;
import com.xfatur.model.Representante;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class RepresentanteServiceTest {

    @Autowired
    private RepresentanteService representanteService;
    private RepresentanteDTO representanteDTO;

    @BeforeAll
    public void createRepresentanteDTO() {

	Representante representante = new Representante();
	representante.setCNPJCPF("77851609000107");
	representante.setxNome("REPRESENTANTE DE VENDA");
	Endereco endereco = new Endereco();
	endereco.setxLgr("Rua Joaquim Macedo");
	endereco.setNro("350");
	endereco.setxCpl("2º andar");
	endereco.setxBairro("Centro");
	endereco.setcMun("123457");
	endereco.setxMun("São Paulo");
	endereco.setUF("SP");
	endereco.setCEP("1234578");
	endereco.setcPais("1247");
	endereco.setxPais("Brasil");
	endereco.setFone("457978978");

	representante.setEndereco(endereco);
	representante.setIE("111222333444");
	representante.setEmail("email@outrosemail.com");

	representanteDTO = DTOConverter.convert(representante);
    }

    @AfterAll
    public void delete() {
	Boolean deletado = representanteService.delete(this.representanteDTO.getId());

	MatcherAssert.assertThat(deletado, Matchers.equalToObject(Boolean.TRUE));

	Boolean delete = this.representanteService.delete(100);

	MatcherAssert.assertThat(delete, Matchers.equalToObject(Boolean.FALSE));

    }

    @Test
    @Order(1)
    public void test_save() {
	RepresentanteDTO saved = this.representanteService.save(representanteDTO);
	this.representanteDTO.setId(saved.getId());

	MatcherAssert.assertThat(saved.getId(), Matchers.greaterThan(0));
    }

    @Test
    @Order(2)
    public void test_findByCNPJCPF() {
	RepresentanteDTO found = this.representanteService.findByCNPJCPF("77851609000107");

	MatcherAssert.assertThat(found.getCNPJCPF(), Matchers.equalToObject("77851609000107"));
	Exception exception = Assertions.assertThrows(RepresentanteNotFoundException.class, () -> {
	    this.representanteService.findByCNPJCPF("12456");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Representante não encontrado"));
    }

    @Test
    @Order(3)
    public void test_findByxNomeLike() {
	List<RepresentanteDTO> found = this.representanteService.buscaPorNome("R");
	MatcherAssert.assertThat(found.size(), Matchers.greaterThan(0));

	found = this.representanteService.buscaPorNome("sss");

	MatcherAssert.assertThat(found.size(), Matchers.equalTo(0));

    }

}
