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
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class EmitenteServiceTest {

    @Autowired
    private EmitenteService service;

    private EmitenteDTO emitenteDTO;

    private EmitenteDTO savedEmitente;

    @BeforeAll
    public void create() {
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
	Boolean result = service.delete(savedEmitente.getId());

	MatcherAssert.assertThat(result, Matchers.equalToObject(Boolean.TRUE));

	result = service.delete(77);
	MatcherAssert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    @Order(1)
    public void test_save() {

	savedEmitente = this.service.save(emitenteDTO);

	MatcherAssert.assertThat(savedEmitente.getId(), Matchers.greaterThan(0));

    }

    @Test
    @Order(2)
    public void test_findById() {
	EmitenteDTO emitenteDTO2 = this.service.findById(savedEmitente.getId());

	MatcherAssert.assertThat(savedEmitente.getId(), Matchers.equalToObject(emitenteDTO2.getId()));

	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> {
	    this.service.findById(100);
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Emitente Não encontrado"));

    }

    @Test
    @Order(3)
    public void test_getAll() {
	List<EmitenteDTO> emits = this.service.getAll();
	MatcherAssert.assertThat(emits.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    public void test_findByCNPJ() {
	EmitenteDTO found = this.service.findByCNPJ("65037603000103");

	MatcherAssert.assertThat(found.getCNPJ(), Matchers.equalToObject("65037603000103"));

	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> {
	    this.service.findByCNPJ("");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Emitente Não encontrado"));

    }

    @Test
    @Order(5)
    public void test_findByxNomeContaining() {
	EmitenteDTO found = this.service.findByxNomeContaining("Empresa de Viagens Ltda");

	MatcherAssert.assertThat(found.getxNome(), Matchers.equalToObject("Empresa de Viagens Ltda"));

	Exception exception = Assertions.assertThrows(EmitenteNotFoundException.class, () -> {
	    this.service.findByxNomeContaining("fdasfdsa");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Emitente Não encontrado"));
    }
}