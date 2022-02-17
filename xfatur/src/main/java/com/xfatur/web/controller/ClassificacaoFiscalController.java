package com.xfatur.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.service.produto.ClassificacaoFiscalService;

@Controller
@RequestMapping("classificacaofiscal")
public class ClassificacaoFiscalController {

    @Autowired
    private ClassificacaoFiscalService service;

    @GetMapping("form")
    public String openForm(ClassificacaoFiscalDTO dto) {

	return "/cadastro/classificacaofiscal";
    }

    @PostMapping("salvar")
    public String salvar(@Valid ClassificacaoFiscalDTO classificacaoFiscal, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/classificacaofiscal";
	}

	service.save(classificacaoFiscal);

	attr.addFlashAttribute("success", "Classificação fiscal incluída.");

	return "redirect:/classificacaofiscal/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	ClassificacaoFiscalDTO classificacaoFiscalDTO = service.findById(id);
	return new ModelAndView("redirect:/classificacaofiscal/form", "classificacaoFiscalDTO", classificacaoFiscalDTO);
    }

}