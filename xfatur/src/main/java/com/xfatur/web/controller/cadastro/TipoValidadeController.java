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
import com.xfatur.service.produto.TipoValidadeService;
import com.xfatur.validation.dto.cadastro.TipoValidadeDTO;

@Controller
@RequestMapping("tipovalidade")
public class TipoValidadeController {

    @Autowired
    private TipoValidadeService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("form")
    public String openForm(TipoValidadeDTO dto) {

	return "/cadastro/tipovalidade";
    }

    @PostMapping("salvar")
    public String salvar(@Valid TipoValidadeDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/tipovalidade";
	}

	service.save(mapper.toModel(dto));

	attr.addFlashAttribute("success", "Tipo Validade incluída.");

	return "redirect:/tipovalidade/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	TipoValidadeDTO dto = mapper.toDto(service.findById(id));

	return new ModelAndView("/cadastro/tipovalidade", "tipoValidadeDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid TipoValidadeDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/tipovalidade";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Tipo Validade alterada.");

	return "redirect:/tipovalidade/form";
    }
}