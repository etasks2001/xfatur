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

import com.xfatur.service.produto.TributacaoService;
import com.xfatur.validation.dto.cadastro.TributacaoDTO;

@Controller
@RequestMapping("tributacao")
public class TributacaoController {

    @Autowired
    private TributacaoService service;

    @GetMapping("form")
    public String openForm(TributacaoDTO dto) {

	return "/cadastro/tributacao";
    }

    @PostMapping("salvar")
    public String salvar(@Valid TributacaoDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/tributacao";
	}

	service.save(dto);

	attr.addFlashAttribute("success", "Tributação incluída.");

	return "redirect:/tributacao/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	TributacaoDTO dto = service.findById(id);

	return new ModelAndView("/cadastro/tributacao", "tributacaoDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid TributacaoDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/tributacao";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Tributação alterada.");

	return "redirect:/tributacao/form";
    }
}