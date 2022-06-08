package com.xfatur.web.controller.cadastro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.base.BaseTest;

@DisplayName("Controller - Cadastro")
class CadastroControllerTest extends BaseTest {

    @Test
    @DisplayName("GET /cadastros")
    void openForm() throws Exception {

	Assertions.assertThat(mock).isNotNull();

	mock.perform(get("/cadastros")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(status().isOk())

	;

    }

    @Test
    @DisplayName("GET /cadastros - acesso negado")
    @WithMockUser(username = "msergiost@homail.com", authorities = { "FINANCEIRO", "FISCAL" })
    void openForm_acesso_negado() throws Exception {
	mock.perform(get("/cadastros").with(SecurityMockMvcRequestPostProcessors.csrf()))

		.andExpect(status().is4xxClientError())

		.andExpect(MockMvcResultMatchers.forwardedUrl("/acesso-negado"))

	;
    }
}