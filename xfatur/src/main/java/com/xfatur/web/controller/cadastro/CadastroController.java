package com.xfatur.web.controller.cadastro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cadastros")
public class CadastroController {

    @GetMapping()
    public String openForm() {

	return "/cadastro/cadastro";
    }

}
