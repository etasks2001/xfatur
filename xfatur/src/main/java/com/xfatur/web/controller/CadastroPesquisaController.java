package com.xfatur.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cadastro")
public class CadastroPesquisaController {

    @GetMapping("pesquisar/{cadastro}")
    public String openForm(@PathVariable("cadastro") String cadastro) {

	System.out.println(cadastro);

	return "/cadastro/pesquisa";

    }

}
