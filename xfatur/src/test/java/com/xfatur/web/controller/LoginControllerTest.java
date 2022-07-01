package com.xfatur.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.base.BaseTest;

@DisplayName("Controller - Login")
public class LoginControllerTest extends BaseTest {

    @Test
    @DisplayName("POST /login")
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    @WithMockUser(username = "email@email.com.br", authorities = { "FATURAMENTO" })
    void login_test() throws Exception {

	mock.perform(post("/login")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("username", "email@email.com.br")

		.param("password", "123")

		.with(csrf())

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/home"))

	;

    }

    @Test
    @DisplayName("POST /login - falhou")
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void login_falhou_test() throws Exception {

	mock.perform(post("/login")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("username", "email@email.com.br")

		.param("password", "12")

		.with(csrf())

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/login-error"))

	;

    }

}
