package com.xfatur.web.controller.cadastro;

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

import com.xfatur.service.produto.TipoSeloService;
import com.xfatur.validation.dto.cadastro.TipoSeloDTO;

@Controller
@RequestMapping("tiposelo")
public class TipoSeloController {

    @Autowired
    private TipoSeloService service;

    @GetMapping("form")
    public String openForm(TipoSeloDTO dto) {

	return "/cadastro/tiposelo";
    }

    @PostMapping("salvar")
    public String salvar(@Valid TipoSeloDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/tiposelo";
	}

	service.save(dto);

	attr.addFlashAttribute("success", "Tipo de Selo incluído.");

	return "redirect:/tiposelo/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	TipoSeloDTO dto = service.findById(id);

	return new ModelAndView("/cadastro/tiposelo", "tipoSeloDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid TipoSeloDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/tiposelo";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Tipo de Selo alterado.");

	return "redirect:/tiposelo/form";
    }
}