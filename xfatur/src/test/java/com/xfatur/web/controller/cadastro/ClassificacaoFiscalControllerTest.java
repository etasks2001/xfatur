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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    @DisplayName("GET /classificacaofiscal/form >>> abrindo formulário")
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
    @DisplayName("GET /classificacaofiscal/editar/{id} localizando id com sucesso e retornando formulário preenchido")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void localizando_id_com_sucesso_e_retornando_formulario_preenchido_test() throws Exception {

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
    @DisplayName("GET /classificacaofiscal/editar/{id} localizando id sem sucesso e retornando formulário preenchido")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void localizando_id_sem_sucesso_e_retornando_formulario_preenchido_test() throws Exception {

	Integer id = 879765467;

	mock.perform(get("/classificacaofiscal/editar/{id}", id))

		.andExpect(view().name("error"))

		.andExpect(status().isOk())

		.andExpect(model().attribute("message", "Código da classificação fiscal não encontrado."))

		.andExpect(model().attribute("status", "404"))

		.andExpect(model().attribute("error", "Operação não pode ser realizada."))

	;
    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> alterar cadastro sem erros nos campos")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void alterar_cadastro_sem_erros_nos_campos() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "1111.1111")

		.param("descricao", "alterado")

		.sessionAttr("dto", new ClassificacaoFiscalDTO())

	)

		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())

		.andExpect(view().name("redirect:/classificacaofiscal/form"))

		.andExpect(flash().attribute("success", is("Classificação fiscal alterada.")))

		.andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> alterar cadastro com erros nos campos")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void alterar_cadastro_com_erros_nos_campos() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "")

		.param("descricao", "")

	)

		.andExpect(status().isOk())

		.andExpect(view().name("cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("id", is(id))))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("ncm", is(""))))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("descricao", is(""))))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "ncm"))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "descricao"))

	;

    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> alterar cadastro - ncm e descricao já cadastrados")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    @Sql(scripts = {
	    "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8", transactionMode = TransactionMode.DEFAULT))
    void alterar_cadastro_ncm_e_descricao_ja_cadastrados() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "4587.9314")

		.param("descricao", "SORVETE DE COPO")

	)

		.andExpect(status().isOk())

		.andExpect(view().name("cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("id", is(id))))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("ncm", is("4587.9314"))))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("descricao", is("SORVETE DE COPO"))))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "ncm"))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "descricao"))

	;

    }

}
