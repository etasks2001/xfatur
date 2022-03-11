package com.xfatur.web.controller.cadastro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cadastros")
public class CadastroController {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping()
    public String openForm() {

	System.out.println(activeProfile);

	return "/cadastro/cadastro";
    }

}
