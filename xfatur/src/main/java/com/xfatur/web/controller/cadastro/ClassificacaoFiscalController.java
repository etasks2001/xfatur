package com.xfatur.web.controller.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;

@Controller
@RequestMapping("classificacaofiscal")
public class ClassificacaoFiscalController {

    @Autowired
    private ClassificacaoFiscalService service;

    @GetMapping("form")
    public String openForm(Model model) {
	model.addAttribute("classificacaofiscal", new ClassificacaoFiscalDTO());

	return "/cadastro/classificacaofiscal";
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid ClassificacaoFiscalDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {

	    ModelAndView modelAndView = new ModelAndView("cadastro/classificacaofiscal");

	    modelAndView.addObject("classificacaofiscal", dto);

	    return modelAndView;
	}

	service.save(dto);

	attr.addFlashAttribute("success", "Classificação fiscal incluída.");

	return new ModelAndView("redirect:/classificacaofiscal/form");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {

	ClassificacaoFiscalDTO dto = service.findById(id);

	return new ModelAndView("/cadastro/classificacaofiscal", "classificacaofiscal", dto);
    }

    @PostMapping("alterar")
    public ModelAndView alterar(@Valid ClassificacaoFiscalDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {

	    ModelAndView modelAndView = new ModelAndView("cadastro/classificacaofiscal");

	    modelAndView.addObject("classificacaofiscal", dto);

	    return modelAndView;
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Classificação fiscal alterada.");

	return new ModelAndView("redirect:/classificacaofiscal/form");
    }
}