package com.xfatur.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xfatur.model.produto.ClassificacaoFiscal;

@Controller
@RequestMapping("classificacaofiscal")
public class CadastroClassificacaoFiscalController {

    @GetMapping
    public String openForm(ModelMap model) {
	ClassificacaoFiscal classificacaoFiscal = new ClassificacaoFiscal();
	classificacaoFiscal.setNcm("12345678");
	classificacaoFiscal.setDescricao("LEITE NINHO");

	model.addAttribute(classificacaoFiscal);

	return "/cadastro/classificacaofiscal";
    }

}
