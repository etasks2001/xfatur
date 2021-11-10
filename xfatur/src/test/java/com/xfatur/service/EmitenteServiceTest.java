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

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class EmitenteServiceTest {

    private static final String NAO_ENCONTRADO = "Não encontrado";

    @Autowired
    private EmitenteService emitenteService;

    private EmitenteDTO emitenteDTO;

    private EmitenteDTO savedEmitente;

    @BeforeAll
    public void createEmitDTO() {
	Emitente emitente = new Emitente();
	emitente.setCNPJ("65037603000103");
	emitente.setxNome("Empresa de Viagens Ltda");
	emitente.setxFant("Emp Via");

	Endereco enderecoEmitente = new Endereco();
	enderecoEmitente.setxLgr("Rua Graça Saldanha");
	enderecoEmitente.setNro("150");
	enderecoEmitente.setxCpl("9º - sala 9000");
	enderecoEmitente.setxBairro("Centro");
	enderecoEmitente.setcMun("0000000");
	enderecoEmitente.setxMun("São Paulo");
	enderecoEmitente.setUF("SP");
	enderecoEmitente.setCEP("12345678");
	enderecoEmitente.setcPais("4678");
	enderecoEmitente.setxPais("Brasil");
	enderecoEmitente.setFone("(11) 2254-8787");

	emitente.setEnderEmit(enderecoEmitente);
	emitente.setIE("123456789");
	emitente.setIEST("");
	emitente.setIM("12345678");
	emitente.setCNAE("7654321");
	emitente.setCRT("1");
	emitente.setNf_serie_atual(0);
	emitente.setUltima_nnf(25454);

	emitenteDTO = DTOConverter.convert(emitente);
    }

    @AfterAll
    public void delete() {
	Boolean result = emitenteService.delete(savedEmitente.getId());

	MatcherAssert.assertThat(result, Matchers.equalToObject(Boolean.TRUE));

	result = emitenteService.delete(77);
	MatcherAssert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    @Order(1)
    public void test_save() {

	savedEmitente = this.emitenteService.save(emitenteDTO);

	MatcherAssert.assertThat(savedEmitente.getId(), Matchers.greaterThan(0));

	System.out.println(savedEmitente.getId());
    }

    @Test
    @Order(2)
    public void test_findById() {

	EmitenteDTO emitenteDTO2 = this.emitenteService.findById(savedEmitente.getId());

	MatcherAssert.assertThat(savedEmitente.getId(), Matchers.equalToObject(emitenteDTO2.getId()));

	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> {
	    this.emitenteService.findById(100);
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject(NAO_ENCONTRADO));

    }

    @Test
    @Order(3)
    public void test_getAll() {
	List<EmitenteDTO> emits = this.emitenteService.getAll();
	MatcherAssert.assertThat(emits.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    public void test_findByCNPJ() {
	String cnpj = "65037603000103";
	EmitenteDTO found = this.emitenteService.findByCNPJ(cnpj);

	MatcherAssert.assertThat(found.getCNPJ(), Matchers.equalToObject(cnpj));

	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> {
	    this.emitenteService.findByCNPJ("");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject(NAO_ENCONTRADO));

    }

    @Test
    @Order(5)
    public void test_queryByxName() {
	String nome = "Empresa de Viagens Ltda";
	EmitenteDTO found = this.emitenteService.queryByxName(nome);

	MatcherAssert.assertThat(found.getxNome(), Matchers.equalToObject(nome));

	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> {
	    this.emitenteService.queryByxName("");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject(NAO_ENCONTRADO));

    }
}