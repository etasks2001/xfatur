package com.xfatur.web.controller.security;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.base.BaseTest;
import com.xfatur.model.security.Usuario;
import com.xfatur.service.security.UsuarioService;

class UsuarioControllerTest extends BaseTest {

    @Autowired
    private UsuarioService service;

    @Test
    @DisplayName("GET u/cadastro/form >> abre formulário")
    void abre_formulário() throws Exception {

	mock.perform(MockMvcRequestBuilders.get("/u/cadastro/form")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())

		.andExpect(MockMvcResultMatchers.view().name("usuario/cadastro"))

		.andExpect(MockMvcResultMatchers.model().attributeExists("usuarioDTO"))

		.andExpect(MockMvcResultMatchers.model().attribute("usuarioDTO",

			Matchers.allOf(

				Matchers.hasProperty("id", Matchers.nullValue()),

				Matchers.hasProperty("email", Matchers.nullValue()),

				Matchers.hasProperty("senha", Matchers.nullValue()),

				Matchers.hasProperty("perfisDTO", Matchers.empty())

			)

		))

	;
    }

    @Test
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    @DisplayName("POST u/cadastro/alterar >> alterar registro ok")
    void alterar_usuario() throws Exception {
	Usuario usuario = service.buscarPorEmail("email@email.com.br");

	mock.perform(MockMvcRequestBuilders.post("/u/cadastro/alterar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("id", "" + usuario.getId())

		.param("email", "email@email.uol.com.br")

		.param("ativo", "true")

		.param("perfisDTO", "1")

		.param("perfisDTO", "2")

	)

		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.view().name("redirect:/u/lista"))

		.andExpect(flash().attribute("sucesso", is("Alterado com sucesso.")))

		.andExpect(MockMvcResultMatchers.redirectedUrl("/u/lista"))

	;
    }
}