package com.xfatur.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@WebMvcTest(LoginController.class)
@DisplayName("Controller - Login")
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /login")
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
