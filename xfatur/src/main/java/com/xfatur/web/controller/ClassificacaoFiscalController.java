package com.xfatur.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.service.produto.ClassificacaoFiscalService;

@Controller
@RequestMapping("classificacaofiscal")
public class ClassificacaoFiscalController {

    @Autowired
    private ClassificacaoFiscalService service;

    @GetMapping
    public String openForm(ClassificacaoFiscal classificacaoFiscal) {

	return "/cadastro/classificacaofiscal";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid ClassificacaoFiscal classificacaoFiscal, BindingResult result) {
	if (result.hasErrors()) {
	    return "cadastro/classificacaofiscal";
	}

	service.save(classificacaoFiscal);

	return "redirect:/classificacaofiscal";
    }

}
