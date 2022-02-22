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

import com.xfatur.dto.produto.UnidadeDTO;
import com.xfatur.mappers.ModelMapper;
import com.xfatur.service.produto.UnidadeService;

@Controller
@RequestMapping("unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("form")
    public String openForm(UnidadeDTO dto) {

	return "/cadastro/unidade";
    }

    @PostMapping("salvar")
    public String salvar(@Valid UnidadeDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/unidade";
	}

	service.save(mapper.toModel(dto));

	attr.addFlashAttribute("success", "Unidade incluída.");

	return "redirect:/unidade/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	UnidadeDTO dto = mapper.toDto(service.findById(id));

	return new ModelAndView("/cadastro/unidade", "unidadeDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid UnidadeDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/unidade";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Unidade alterada.");

	return "redirect:/unidade/form";
    }
}