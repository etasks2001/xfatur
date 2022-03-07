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

import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.service.produto.MarcaService;
import com.xfatur.validation.dto.cadastro.MarcaDTO;

@Controller
@RequestMapping("marca")
public class MarcaController {

    @Autowired
    private MarcaService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("form")
    public String openForm(MarcaDTO dto) {

	return "/cadastro/marca";
    }

    @PostMapping("salvar")
    public String salvar(@Valid MarcaDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/marca";
	}

	service.save(mapper.toModel(dto));

	attr.addFlashAttribute("success", "Marca incluída.");

	return "redirect:/marca/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	MarcaDTO dto = mapper.toDto(service.findById(id));

	return new ModelAndView("/cadastro/marca", "marcaDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid MarcaDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/marca";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Marca alterada.");

	return "redirect:/marca/form";
    }
}