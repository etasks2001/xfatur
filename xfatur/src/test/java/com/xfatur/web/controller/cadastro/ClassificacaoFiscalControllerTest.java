package com.xfatur.web.controller.cadastro;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.xfatur.service.produto.ClassificacaoFiscalService;

//@WebMvcTest(ClassificacaoFiscalController.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClassificacaoFiscalControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private ClassificacaoFiscalService service;

    @Test
    @DisplayName("GET /classificacaofiscal/form success")
    void test() throws Exception {
	Assertions.assertNotNull(mock, "Formulário encontrado");

	mock.perform(get("/classificacaofiscal/form"))

		.andExpect(status().isOk())

		.andExpect(view().name("/cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("id", nullValue())))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("ncm", nullValue())))

		.andExpect(model().attribute("classificacaofiscal", hasProperty("descricao", nullValue())));

    }

}
