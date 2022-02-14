package com.xfatur.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.service.produto.ClassificacaoFiscalService;

@Controller
@RequestMapping("cadastro")
public class CadastroPesquisaController {
    private String[] cols = new String[] { "id", "ncm", "descricao" };

    @Autowired
    private ClassificacaoFiscalService service;

    @GetMapping("pesquisar/{cadastro}")
    public String openForm(@PathVariable("cadastro") String cadastro, ModelMap model) {

	model.addAttribute("cadastro", cadastro);

	System.out.println(cadastro);

	return "/cadastro/pesquisa";

    }

    @GetMapping("/pesquisar/datatables")
    public ResponseEntity<?> datatables(HttpServletRequest request) {
	Map<String, Object> data = execute(service, request);
	return ResponseEntity.ok(data);
    }

    public Map<String, Object> execute(ClassificacaoFiscalService service, HttpServletRequest request) {

	int start = Integer.parseInt(request.getParameter("start"));
	int length = Integer.parseInt(request.getParameter("length"));
	int draw = Integer.parseInt(request.getParameter("draw"));
	int current = currentPage(start, length);
	String column = columnName(request);
	Sort.Direction direction = orderBy(request);
	String search = searchBy(request);

	Pageable pageable = PageRequest.of(current, length, direction, column);

	Page<ClassificacaoFiscal> page = queryBy(search, service, pageable, column);

	Map<String, Object> json = new LinkedHashMap<String, Object>();

	json.put("draw", draw);
	json.put("recordsTotal", page.getTotalElements());
	json.put("recordsFiltered", page.getTotalElements());
	json.put("data", page.getContent());

	return json;

    }

    private String searchBy(HttpServletRequest request) {
	String parameter = request.getParameter("search[value]");
	if (parameter.isEmpty()) {
	    return "";
	}

	return parameter.toUpperCase();
    }

    private Page<ClassificacaoFiscal> queryBy(String search, ClassificacaoFiscalService repository, Pageable pageable, String column) {

	if (column.equals("ncm")) {
	    return repository.findByNcm(search, pageable);

	}
	return repository.findByDescricao(search, pageable);

    }

    private Direction orderBy(HttpServletRequest request) {
	String order = request.getParameter("order[0][dir]");
	Sort.Direction sort = Sort.Direction.ASC;

	if (order.equalsIgnoreCase("desc")) {
	    sort = Sort.Direction.DESC;
	}

	return sort;
    }

    private String columnName(HttpServletRequest request) {
	int iCol = Integer.parseInt(request.getParameter("order[0][column]"));
	return cols[iCol];
    }

    private int currentPage(int start, int length) {
	// - 1 | 2 | 3
	// 0-9|10-19|20-29
	return start / length;
    }

}
