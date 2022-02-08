package com.xfatur.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cadastro")
public class CadastroPesquisaController {

    @GetMapping("pesquisar")
    public String openForm() {

	return "/cadastro/pesquisa";

    }

}
