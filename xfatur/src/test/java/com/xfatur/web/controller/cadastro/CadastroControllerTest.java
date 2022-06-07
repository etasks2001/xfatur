package com.xfatur.web.controller.cadastro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.xfatur.XFaturApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { XFaturApplication.class })
@AutoConfigureMockMvc
@WithMockUser(username = "msergiost@homail.com", authorities = { "FATURAMENTO" })
@ActiveProfiles("dev")
@DisplayName("Controller - Cadastro")
class CadastroControllerTest {

    @Autowired
    private MockMvc mock;

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
