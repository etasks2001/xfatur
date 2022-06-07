package com.xfatur.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.base.BaseTest;

@DisplayName("Controller - Login")
public class LoginControllerTest extends BaseTest {

    @Test
    @DisplayName("POST /login")
    @WithMockUser(username = "msergiost@hotmail.com", authorities = { "FATURAMENTO" })
    void login_test() throws Exception {

	mock.perform(post("/login")

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

	mock.perform(post("/login")

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
