package com.xfatur.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@DisplayName("Controller - Login")
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @BeforeEach
    public void setup() {
	mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).addFilters(filterChainProxy).build();
    }

    @Test
    @DisplayName("POST /login")
    @WithMockUser(username = "msergiost@hotmail.com", authorities = { "FATURAMENTO" })
    void login_test() throws Exception {

	mockMvc.perform(post("/login")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("username", "msergiost@hotmail.com")

		.param("password", "123")

		.with(csrf())

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/home"))

	;

    }

    @Test
    @DisplayName("POST /login - falhou")
    void login_falhou_test() throws Exception {

	mockMvc.perform(post("/login")

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("username", "msergiost@hotmail.com")

		.param("password", "12")

		.with(csrf())

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/login-error"))

	;

    }

}
