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

import com.xfatur.service.produto.OrigemService;
import com.xfatur.validation.dto.cadastro.OrigemDTO;

@Controller
@RequestMapping("origem")
public class OrigemController {

    @Autowired
    private OrigemService service;

    @GetMapping("form")
    public String openForm(OrigemDTO dto) {

	return "/cadastro/origem";
    }

    @PostMapping("salvar")
    public String salvar(@Valid OrigemDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/origem";
	}

	service.save(dto);

	attr.addFlashAttribute("success", "Origem incluída.");

	return "redirect:/origem/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	OrigemDTO dto = service.findById(id);

	return new ModelAndView("/cadastro/origem", "origemDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid OrigemDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/origem";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Origem alterada.");

	return "redirect:/origem/form";
    }
}