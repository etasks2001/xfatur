package com.xfatur.web.controller.cadastro;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xfatur.XFaturApplication;
import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;

@SpringBootTest(classes = { XFaturApplication.class })
@ActiveProfiles("dev")
@WithMockUser(username = "msergiost@hotail.com", roles = { "FINANCEIRO", "FATURAMENTO", "FISCAL" })
@DisplayName("Controller - Classificação Fiscal")
class ClassificacaoFiscalControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mock;

    @BeforeEach
    public void setup() {
	mock = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @MockBean
    private ClassificacaoFiscalService service;

    @Test
    @DisplayName("GET /classificacaofiscal/form >> abrir formulário")
    void openForm_test() throws Exception {
	Assertions.assertNotNull(mock, "Formulário encontrado");

	mock.perform(get("/classificacaofiscal/form")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(status().isOk())

		.andExpect(view().name("/cadastro/classificacaofiscal"))

		.andExpect(model().attributeExists("classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", allOf(

			hasProperty("id", nullValue()),

			hasProperty("ncm", nullValue()),

			hasProperty("descricao", nullValue()))

		))

		.andExpect(content().string(containsString("action=\"/classificacaofiscal/salvar\"")))

	;

    }

    @Test
    @DisplayName("POST /classificacaofiscal/salvar")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void gravar() throws Exception {

	mock.perform(post("/classificacaofiscal/salvar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(APPLICATION_FORM_URLENCODED)

		.param("id", "")

		.param("ncm", "9999.9999")

		.param("descricao", "ultimo")

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(view().name("redirect:/classificacaofiscal/form"))

		.andExpect(flash().attribute("success", is("Classificação fiscal incluída.")))

	;
    }

    @Test
    @DisplayName("POST /classificacaofiscal/salvar >> em branco")
    void gravar_em_branco() throws Exception {

	mock.perform(post("/classificacaofiscal/salvar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(APPLICATION_FORM_URLENCODED)

		.param("id", "")

		.param("ncm", "")

		.param("descricao", "")

	)

		.andExpect(status().isOk())

		.andExpect(view().name("cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", allOf(

			hasProperty("id", nullValue()),

			hasProperty("ncm", is("")),

			hasProperty("descricao", is("")))

		))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "ncm", "descricao"))

	;
    }

    @Test
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    @DisplayName("POST /classificacaofiscal/salvar >> registro já existente")
    void gravar_registro_ja_existente() throws Exception {

	mock.perform(post("/classificacaofiscal/salvar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(APPLICATION_FORM_URLENCODED)

		.param("id", "")

		.param("ncm", "1234.5678")

		.param("descricao", "SORVETE DE COPO")

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(view().name("redirect:/classificacaofiscal/form"))

		.andExpect(model().attribute("classificacaofiscal", allOf(

			hasProperty("id", nullValue()),

			hasProperty("ncm", is("1234.5678")),

			hasProperty("descricao", is("SORVETE DE COPO")))

		))

		.andExpect(model().errorCount(2))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "ncm", "descricao"))

		.andExpect(content().string(allOf(containsString("NCM já cadastrado."), containsString("Descrição já cadastrada."))))

	;
    }

    @Test
    @DisplayName("GET /classificacaofiscal/editar/{id}")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void editar_id() throws Exception {

	Integer id = service.findIdByDescricao("SORVETE DE MASSA");
	ClassificacaoFiscalDTO dto = new ClassificacaoFiscalDTO();
	dto.setId(id);
	dto.setDescricao("SORVETE DE MASSA");
	dto.setNcm("1234.5678");

	Mockito.when(service.findById(id)).thenReturn(dto);

	mock.perform(get("/classificacaofiscal/editar/{id}", id)

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(status().isOk())

		.andExpect(view().name("/cadastro/classificacaofiscal"))

		.andExpect(model().attributeExists("classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", allOf(

			hasProperty("id", is(id)),

			hasProperty("ncm", is("1234.5678")),

			hasProperty("descricao", is("SORVETE DE MASSA")))))

		.andExpect(content().string(containsString("action=\"/classificacaofiscal/alterar\"")))

	;
    }

    @Test
    @DisplayName("GET /classificacaofiscal/editar/{id} >> inexistente")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))

    void editar_id_inexistente() throws Exception {

	Integer id = 879765467;

	mock.perform(get("/classificacaofiscal/editar/{id}", id)

		.with(SecurityMockMvcRequestPostProcessors.csrf())

	)

		.andExpect(status().isOk())

		.andExpect(view().name("error"))

		.andExpect(model().attribute("message", "Código da classificação fiscal não encontrado."))

		.andExpect(model().attribute("status", "404"))

		.andExpect(model().attribute("error", "Operação não pode ser realizada."))

	;
    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void alterar_cadastro() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "1111.1111")

		.param("descricao", "alterado")

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(view().name("redirect:/classificacaofiscal/form"))

		.andExpect(flash().attribute("success", is("Classificação fiscal alterada.")))

	;
    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> em branco")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void alterar_cadastro_com_erros_nos_campos() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "")

		.param("descricao", "")

	)

		.andExpect(status().isOk())

		.andExpect(view().name("cadastro/classificacaofiscal"))

		.andExpect(model().attribute("classificacaofiscal", allOf(

			hasProperty("id", is(id)),

			hasProperty("ncm", is("")),

			hasProperty("descricao", is(""))

		)))

		.andExpect(model().errorCount(2))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "ncm", "descricao"))

		.andExpect(content().string(containsString("action=\"/classificacaofiscal/alterar\"")))

	;

    }

    @Test
    @DisplayName("POST /classificacaofiscal/alterar >> registro já existente")
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal.sql" }, config = @SqlConfig(encoding = "UTF-8"))
    @Sql(scripts = { "classpath:/cadastro/classificacaofiscal-clean.sql" }, executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(encoding = "UTF-8"))
    void alterar_cadastro_para_outro_ja_existente() throws Exception {
	Integer id = service.findIdByDescricao("SORVETE DE MASSA");

	mock.perform(post("/classificacaofiscal/alterar")

		.with(SecurityMockMvcRequestPostProcessors.csrf())

		.contentType(APPLICATION_FORM_URLENCODED)

		.param("id", String.valueOf(id))

		.param("ncm", "4587.9314")

		.param("descricao", "SORVETE DE COPO")

	)

		.andExpect(status().is3xxRedirection())

		.andExpect(view().name("redirect:/classificacaofiscal/form"))

		.andExpect(model().attribute("classificacaofiscal", allOf(

			hasProperty("id", is(id)),

			hasProperty("ncm", is("4587.9314")),

			hasProperty("descricao", is("SORVETE DE COPO")))))

		.andExpect(model().errorCount(2))

		.andExpect(model().attributeHasFieldErrors("classificacaofiscal", "ncm", "descricao"))

		.andExpect(content().string(allOf(containsString("NCM já cadastrado."), containsString("Descrição já cadastrada."))))

		.andExpect(content().string(containsString("action=\"/classificacaofiscal/alterar\"")))

	;
    }
}