package com.xfatur.web.controller.security;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import java.util.List;

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
import com.xfatur.model.security.Perfil;
import com.xfatur.model.security.Usuario;
import com.xfatur.service.security.UsuarioService;

class UsuarioControllerTest extends BaseTest {

    @Autowired
    private UsuarioService service;

    @Test
    @DisplayName("GET u/cadastro/form >> abre formulário")
    void abre_formulário() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/u/cadastro/form")

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
    @DisplayName("POST u/cadastro/alterar/credenciais >> alterar registro ok")
    void alterar_credenciais() throws Exception {
	Usuario usuario = service.buscarPorEmail("email@email.com.br");

	mockMvc.perform(MockMvcRequestBuilders.post("/u/cadastro/alterar/credenciais")

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

    @Test
    @DisplayName("POST u/cadastro/gravar >> Ok")
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void test_gravarUsuario() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.post("/u/cadastro/gravar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("email", "email@email.com.br")

		.param("perfisDTO", "1")

		.param("perfisDTO", "2")

	)

		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.redirectedUrl("/u/cadastro/novo"))

		.andExpect(MockMvcResultMatchers.flash().attributeExists("sucesso"))

		.andExpect(MockMvcResultMatchers.view().name("redirect:/u/cadastro/novo"))

	;
    }

    @Test
    @DisplayName("POST u/cadastro/gravar >> Erro - usuario já existe")
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void test_gravarUsuario_ja_cadastrado() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.post("/u/cadastro/gravar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		.param("email", "email@email.com.br")

		.param("perfisDTO", "1")

		.param("perfisDTO", "2")

	)

		.andExpect(MockMvcResultMatchers.status().is3xxRedirection())

		.andExpect(MockMvcResultMatchers.flash().attribute("falha", "E-mail já existe."))

		.andExpect(MockMvcResultMatchers.view().name("redirect:/u/cadastro/novo"))

		.andExpect(MockMvcResultMatchers.redirectedUrl("/u/cadastro/novo"))

	;

    }

    @Test
    @DisplayName("GET u/lista")
    void test_listarUsuarios() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/u/lista")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(MockMvcResultMatchers.status().isOk())

		.andExpect(MockMvcResultMatchers.view().name("usuario/lista"))

		.andExpect(MockMvcResultMatchers.content().string(Matchers.notNullValue()))

	;

    }

    @Test
    @DisplayName("GET u/editar/credenciais/{id} - Encontrado")
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void test_editarCredenciais() throws Exception {
	Usuario usuario = service.buscarPorEmail("email@email.com.br");
	List<Perfil> perfis = usuario.getPerfis();

	mockMvc.perform(MockMvcRequestBuilders.get("/u/editar/credenciais/{id}", usuario.getId())

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(MockMvcResultMatchers.status().isOk())

		.andExpect(MockMvcResultMatchers.view().name("usuario/cadastro"))

		.andExpect(MockMvcResultMatchers.model().attributeExists("usuarioDTO"))

		.andExpect(MockMvcResultMatchers.model().attribute("usuarioDTO",

			allOf(

				hasProperty("id", is(usuario.getId())),

				hasProperty("email", is(usuario.getEmail())),

				hasProperty("perfisDTO", Matchers.notNullValue())

			)

		))

	;

    }

    @Test
    @DisplayName("GET u/pesquisa/datatables")
    @Sql(scripts = { "classpath:/usuario.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/usuario-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void test_listarUsuariosDataTables() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/u/pesquisa/datatables")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.param("0", "d")

		.param("1", "a")

		.param("2", "t")

		.param("3", "a")

		.param("draw", "1")

		.param("columns[0][data]", "id")

		.param("columns[0][name]", "")

		.param("columns[0][searchable]", "true")

		.param("columns[0][orderable]", "true")

		.param("columns[0][search][value]", "")

		.param("columns[0][search][regex]", "false")

		.param("columns[1][data]", "email")

		.param("columns[1][name]", "")

		.param("columns[1][searchable]", "true")

		.param("columns[1][orderable]", "true")

		.param("columns[1][search][value]", "")

		.param("columns[1][search][regex]", "false")

		.param("columns[2][data]", "ativo")

		.param("columns[2][name]", "")

		.param("columns[2][searchable]", "true")

		.param("columns[2][orderable]", "true")

		.param("columns[2][search][value]", "")

		.param("columns[2][search][regex]", "false")

		.param("columns[3][data]", "perfis")

		.param("columns[3][name]", "")

		.param("columns[3][searchable]", "true")

		.param("columns[3][orderable]", "false")

		.param("columns[3][search][value]", "")

		.param("columns[3][search][regex]", "false")

		.param("columns[4][data]", "id")

		.param("columns[4][name]", "")

		.param("columns[4][searchable]", "true")

		.param("columns[4][orderable]", "false")

		.param("columns[4][search][value]", "")

		.param("columns[4][search][regex]", "false")

		.param("order[0][column]", "0")

		.param("order[0][dir]", "asc")

		.param("start", "0")

		.param("length", "5")

		.param("search[value]", "")

		.param("search[regex]", "false")

		.param("_", "1656942780213")

	)

		.andExpect(MockMvcResultMatchers.status().isOk())

		.andExpect(MockMvcResultMatchers.jsonPath("$.draw", Matchers.is(1)))

		.andExpect(MockMvcResultMatchers.jsonPath("$.recordsTotal", Matchers.greaterThan(0)))

		.andExpect(MockMvcResultMatchers.jsonPath("$.recordsFiltered", Matchers.greaterThan(0)))

		.andExpect(MockMvcResultMatchers.jsonPath("$.data.length()", Matchers.greaterThan(0)))

		.andExpect(MockMvcResultMatchers.content().contentType("application/json"))

	;

    }

}