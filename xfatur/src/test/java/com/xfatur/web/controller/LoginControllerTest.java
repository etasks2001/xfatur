package com.xfatur.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Enumeration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.base.BaseTest;

@DisplayName("Controller - Login")
public class LoginControllerTest extends BaseTest {

    @Test
    @DisplayName("GET")
    void test_index() throws Exception {
	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders

		.get("/")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(MockMvcResultMatchers.status().isOk())

		.andExpect(MockMvcResultMatchers.view().name("home")).andReturn()

	;

	MockHttpSession mockHttpSession = (MockHttpSession) mvcResult.getRequest().getSession();

	Enumeration<String> attributeNames = mockHttpSession.getAttributeNames();

	while (attributeNames.hasMoreElements()) {
	    System.out.println(attributeNames.nextElement());
	}

    }

    @Test
    @DisplayName("POST /login")
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    @WithMockUser(username = "email@email.com.br", authorities = { "FATURAMENTO" })
    void login_test() throws Exception {

	mockMvc.perform(post("/login")

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

	mockMvc.perform(post("/login")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("username", "email@email.com.br")

		.param("password", "12")

		.with(csrf())

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/login-error"))

	;

    }

    @Test
    @DisplayName("POST /login - usuario não encontrado")
    void test_login_usuario_nao_encontrado() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders

		.post("/login")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.param("username", "jdfkaslç@fjdskalcdfj.com")

		.param("password", "fdasfda")

	)

		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/login-error"))

	;
    }

}
