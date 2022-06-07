package com.xfatur.web.controller.cadastro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

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

}
