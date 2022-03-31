package com.xfatur.web.controller.cadastro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest(CadastroController.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller - Cadastro")
class CadastroControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    @DisplayName("GET /cadastros")
    void openForm() throws Exception {

	Assertions.assertThat(mock).isNotNull();

	mock.

		perform(get("/cadastros"))

		.andExpect(status().isOk())

	;

    }

}
