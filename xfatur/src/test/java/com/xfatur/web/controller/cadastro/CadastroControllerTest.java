package com.xfatur.web.controller.cadastro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CadastroController.class)
class CadastroControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    void openForm() throws Exception {
	Assertions.assertThat(mock).isNotNull();
	mock.perform(get("/cadastros")).andExpect(MockMvcResultMatchers.status().isOk());

    }

}
