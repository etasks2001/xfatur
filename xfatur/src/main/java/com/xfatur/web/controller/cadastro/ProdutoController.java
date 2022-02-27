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

import com.xfatur.dto.produto.ProdutoDTO;
import com.xfatur.mappers.ModelMapper;
import com.xfatur.service.produto.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("form")
    public String openForm(ProdutoDTO dto) {

	return "/cadastro/produto";
    }

    @PostMapping("salvar")
    public String salvar(@Valid ProdutoDTO dto, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
	    return "cadastro/produto";
	}

	service.save(mapper.toModel(dto));

	attr.addFlashAttribute("success", "Produto incluído.");

	return "redirect:/produto/form";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
	ProdutoDTO dto = mapper.toDto(service.findById(id));

	return new ModelAndView("/cadastro/produto", "produtoDTO", dto);
    }

    @PostMapping("alterar")
    public String alterar(@Valid ProdutoDTO dto, BindingResult result, RedirectAttributes attr) {

	if (result.hasErrors()) {
	    return "cadastro/produto";
	}

	service.update(dto);

	attr.addFlashAttribute("success", "Produto alterado.");

	return "redirect:/produto/form";
    }
}