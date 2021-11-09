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
import com.xfatur.dto.EmitDTO;
import com.xfatur.exception.EmitNotFoundException;
import com.xfatur.model.Emit;
import com.xfatur.model.Ender;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class EmitServiceTest {

    @Autowired
    private EmitService emitService;

    private EmitDTO emitDTO;

    private EmitDTO savedEmit;

    @BeforeAll
    public void createEmitDTO() {
	Emit emit = new Emit();
	emit.setCNPJ("65037603000103");
	emit.setxNome("Empresa de Viagens Ltda");
	emit.setxFant("Emp Via");

	Ender enderEmit = new Ender();
	enderEmit.setxLgr("Rua Graça Saldanha");
	enderEmit.setNro("150");
	enderEmit.setxCpl("9º - sala 9000");
	enderEmit.setxBairro("Centro");
	enderEmit.setcMun("0000000");
	enderEmit.setxMun("São Paulo");
	enderEmit.setUF("SP");
	enderEmit.setCEP("12345678");
	enderEmit.setcPais("4678");
	enderEmit.setxPais("Brasil");
	enderEmit.setFone("(11) 2254-8787");

	emit.setEnderEmit(enderEmit);
	emit.setIE("123456789");
	emit.setIEST("");
	emit.setIM("12345678");
	emit.setCNAE("7654321");
	emit.setCRT("1");
	emit.setNf_serie_atual(0);
	emit.setUltima_nnf(25454);

	emitDTO = DTOConverter.convert(emit);
    }

    @AfterAll
    public void delete() {
	Boolean result = emitService.delete(savedEmit.getId());

	MatcherAssert.assertThat(result, Matchers.equalToObject(Boolean.TRUE));

	result = emitService.delete(77);
	MatcherAssert.assertThat(result, Matchers.equalTo(Boolean.FALSE));
    }

    @Test
    @Order(1)
    public void test_save() {

	savedEmit = this.emitService.save(emitDTO);

	MatcherAssert.assertThat(savedEmit.getId(), Matchers.greaterThan(0));

	System.out.println(savedEmit.getId());
    }

    @Test
    @Order(2)
    public void test_findById() {

	EmitDTO emitDTO2 = this.emitService.findById(savedEmit.getId());

	MatcherAssert.assertThat(savedEmit.getId(), Matchers.equalToObject(emitDTO2.getId()));

	Exception exception = Assertions.assertThrows(EmitNotFoundException.class, () -> {
	    this.emitService.findById(100);
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Não encontrado"));

    }

    @Test
    @Order(3)
    public void test_getAll() {
	List<EmitDTO> emits = this.emitService.getAll();
	MatcherAssert.assertThat(emits.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    public void test_findByCNPJ() {
	EmitDTO found = this.emitService.findByCNPJ("65037603000103");

	MatcherAssert.assertThat(found.getCNPJ(), Matchers.equalToObject("65037603000103"));

	Exception exception = Assertions.assertThrows(EmitNotFoundException.class, () -> {
	    this.emitService.findByCNPJ("");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Não encontrado"));

    }

    @Test
    @Order(5)
    public void test_queryByxName() {
	EmitDTO found = this.emitService.queryByxName("Empresa de Viagens Ltda");

	MatcherAssert.assertThat(found.getxNome(), Matchers.equalToObject("Empresa de Viagens Ltda"));

	Exception exception = Assertions.assertThrows(EmitNotFoundException.class, () -> {
	    this.emitService.queryByxName("");
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.equalToObject("Não encontrado"));

    }
}