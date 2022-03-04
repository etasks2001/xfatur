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

import com.xfatur.mappers.ModelMapper;
import com.xfatur.service.produto.ProdutorService;
import com.xfatur.validation.dto.cadastro.ProdutorDTO;

@Controller
@RequestMapping("produtor")
public class ProdutorController {

	@Autowired
	private ProdutorService service;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("form")
	public String openForm(ProdutorDTO dto) {

		return "/cadastro/produtor";
	}

	@PostMapping("salvar")
	public String salvar(@Valid ProdutorDTO dto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cadastro/produtor";
		}

		service.save(mapper.toModel(dto));

		attr.addFlashAttribute("success", "Produtor incluído.");

		return "redirect:/produtor/form";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		ProdutorDTO dto = mapper.toDto(service.findById(id));

		return new ModelAndView("/cadastro/produtor", "produtorDTO", dto);
	}

	@PostMapping("alterar")
	public String alterar(@Valid ProdutorDTO dto, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "cadastro/produtor";
		}

		service.update(dto);

		attr.addFlashAttribute("success", "Produtor alterado.");

		return "redirect:/produtor/form";
	}
}