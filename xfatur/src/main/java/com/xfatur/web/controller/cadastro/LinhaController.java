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

import com.xfatur.service.produto.LinhaService;
import com.xfatur.validation.dto.cadastro.LinhaDTO;

@Controller
@RequestMapping("linha")
public class LinhaController {

    @Autowired
    private LinhaService service;

    @GetMapping("form")
    public String openForm(LinhaDTO dto) {

	return "/cadastro/linha";
    }

    @PostMapping("salvar")
    public String salvar(@Valid LinhaDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/linha";
	}

	service.save(dto);

	attr.addFlashAttribute("success", "Linha incluída.");

	return "redirect:/linha/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	LinhaDTO dto = service.findById(id);

	return new ModelAndView("/cadastro/linha", "linhaDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid LinhaDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/linha";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Linha alterada.");

	return "redirect:/linha/form";
    }
}