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
import com.xfatur.service.produto.RegiaoProdutoraService;
import com.xfatur.validation.dto.cadastro.RegiaoProdutoraDTO;

@Controller
@RequestMapping("regiaoprodutora")
public class RegiaoProdutoraController {

	@Autowired
	private RegiaoProdutoraService service;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("form")
	public String openForm(RegiaoProdutoraDTO dto) {

		return "/cadastro/regiaoprodutora";
	}

	@PostMapping("salvar")
	public String salvar(@Valid RegiaoProdutoraDTO dto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cadastro/regiaoprodutora";
		}

		service.save(mapper.toModel(dto));

		attr.addFlashAttribute("success", "Regiao Produtora incluída.");

		return "redirect:/regiaoprodutora/form";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		RegiaoProdutoraDTO dto = mapper.toDto(service.findById(id));

		return new ModelAndView("/cadastro/regiaoprodutora", "regiaoProdutoraDTO", dto);
	}

	@PostMapping("alterar")
	public String alterar(@Valid RegiaoProdutoraDTO dto, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "cadastro/regiaoprodutora";
		}

		service.update(dto);

		attr.addFlashAttribute("success", "Regiao Produtora alterada.");

		return "redirect:/regiaoprodutora/form";
	}
}