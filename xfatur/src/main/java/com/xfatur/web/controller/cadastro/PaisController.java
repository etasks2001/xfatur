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

import com.xfatur.dto.produto.PaisDTO;
import com.xfatur.mappers.ModelMapper;
import com.xfatur.service.produto.PaisService;

@Controller
@RequestMapping("pais")
public class PaisController {

    @Autowired
    private PaisService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("form")
    public String openForm(PaisDTO dto) {

	return "/cadastro/pais";
    }

    @PostMapping("salvar")
    public String salvar(@Valid PaisDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/pais";
	}

	service.save(mapper.toModel(dto));

	attr.addFlashAttribute("success", "Pais incluída.");

	return "redirect:/pais/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	PaisDTO dto = mapper.toDto(service.findById(id));

	return new ModelAndView("/cadastro/pais", "paisDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid PaisDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/pais";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Pais alterada.");

	return "redirect:/pais/form";
    }
}