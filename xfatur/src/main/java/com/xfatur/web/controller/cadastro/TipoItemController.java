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

import com.xfatur.dto.produto.TipoItemDTO;
import com.xfatur.mappers.ModelMapper;
import com.xfatur.service.produto.TipoItemService;

@Controller
@RequestMapping("tipoitem")
public class TipoItemController {

	@Autowired
	private TipoItemService service;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("form")
	public String openForm(TipoItemDTO dto) {

		return "/cadastro/tipoitem";
	}

	@PostMapping("salvar")
	public String salvar(@Valid TipoItemDTO dto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cadastro/tipoitem";
		}

		service.save(mapper.toModel(dto));

		attr.addFlashAttribute("success", "Tipo do Item incluído.");

		return "redirect:/tipoitem/form";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		TipoItemDTO dto = mapper.toDto(service.findById(id));

		return new ModelAndView("/cadastro/tipoitem", "tipoItemDTO", dto);
	}

	@PostMapping("alterar")
	public String alterar(@Valid TipoItemDTO dto, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "cadastro/tipoitem";
		}

		service.update(dto);

		attr.addFlashAttribute("success", "Tipo do item alterado.");

		return "redirect:/tipoitem/form";
	}
}