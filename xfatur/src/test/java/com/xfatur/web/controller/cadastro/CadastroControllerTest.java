package com.xfatur.web.controller.cadastro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroControllerTest {

    @Autowired
    private CadastroController controller;

    @Test
    void openForm() {
	Assertions.assertThat(controller).isNotNull();
    }

}
