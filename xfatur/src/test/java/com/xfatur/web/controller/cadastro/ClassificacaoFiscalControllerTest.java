package com.xfatur.web.controller.cadastro;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.web.servlet.MockMvc;

import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;

//@WebMvcTest(ClassificacaoFiscalController.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClassificacaoFiscalControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ClassificacaoFiscalService service;

    @Test
    @DisplayName("GET /classificacaofiscal/form success")
    void openForm_test() throws Exception {
	Assertions.assertNotNull(mock, "Formulário encontrado");

	mock.perform(get("/classificacaofiscal/form"))

		.andExpect(status().isOk())

		.andExpect(view().name("/cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("id", nullValue())))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("ncm", nullValue())))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("descricao", nullValue())));

    }

    @Test
    @DisplayName("GET /classificacaofiscal/editar/{id}")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void editar_test() throws Exception {

	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(get("/classificacaofiscal/editar/{id}", id))

		.andExpect(status().isOk())

		.andExpect(view().name("/cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("id", is(id))))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("ncm", is("1234.5678"))))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("descricao", is("SORVETE DE MASSA"))))

	;
    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> sem erro nos campos")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void alterar_test_sem_erros_nos_campos() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "1111.1111")

		.param("descricao", "alterado")

		.sessionAttr("dto", new ClassificacaoFiscalDTO())

	)

		.andExpect(view().name("redirect:/classificacaofiscal/form"))

		.andExpect(flash().attribute("success", is("Classificação fiscal alterada.")))

		.andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> com erro nos campos")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void alterar_test_com_erros_nos_campos() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("id", "id")

		.param("ncm", "")

		.param("descricao", "")

	)

		.andExpect(view().name("cadastro/classificacaofiscal"))

	;

    }

}
