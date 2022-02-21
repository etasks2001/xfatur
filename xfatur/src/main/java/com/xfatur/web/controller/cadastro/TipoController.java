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

import com.xfatur.dto.produto.TipoDTO;
import com.xfatur.mappers.ModelMapper;
import com.xfatur.service.produto.TipoService;

@Controller
@RequestMapping("tipo")
public class TipoController {

    @Autowired
    private TipoService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("form")
    public String openForm(TipoDTO dto) {

	return "/cadastro/tipo";
    }

    @PostMapping("salvar")
    public String salvar(@Valid TipoDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/tipo";
	}

	service.save(mapper.toModel(dto));

	attr.addFlashAttribute("success", "Classificação fiscal incluída.");

	return "redirect:/tipo/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	TipoDTO dto = mapper.toDto(service.findById(id));

	return new ModelAndView("/cadastro/tipo", "tipoDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid TipoDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/tipo";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Classificação fiscal alterada.");

	return "redirect:/tipo/form";
    }
}