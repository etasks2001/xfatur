package com.xfatur.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
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

import com.xfatur.repository.pesquisa.QueryBy;

@Controller
@RequestMapping("cadastro")
public class CadastroPesquisaController {

    @Autowired
    private BeanFactory beanFactory;

    @GetMapping("pesquisar/{cadastro}")
    public String openForm(@PathVariable("cadastro") String cadastro, ModelMap model) {
	model.addAttribute("cadastro", cadastro);

	return "/cadastro/pesquisa";
    }

    @GetMapping("/pesquisar/datatables")
    public ResponseEntity<?> datatables(HttpServletRequest request) {
	Map<String, Object> data = execute(request);

	return ResponseEntity.ok(data);
    }

    public Map<String, Object> execute(HttpServletRequest request) {
	String name = request.getParameter("cadname") + "queryby";
	QueryBy<?> queryBy = beanFactory.getBean(name, QueryBy.class);

	String columnName = getColumnName(request, queryBy);

	int start = Integer.parseInt(request.getParameter("start"));
	int length = Integer.parseInt(request.getParameter("length"));
	int draw = Integer.parseInt(request.getParameter("draw"));
	int currentPage = getCurrentPage(start, length);

	Sort.Direction direction = orderBy(request);
	String search = searchBy(request);

	Pageable pageable = PageRequest.of(currentPage, length, direction, columnName);

	Page<?> page = queryBy.execute(search, pageable, columnName);

	Map<String, Object> json = new LinkedHashMap<String, Object>();

	json.put("draw", draw);
	json.put("recordsTotal", page.getTotalElements());
	json.put("recordsFiltered", page.getTotalElements());
	json.put("data", page.getContent());

	return json;

    }

    private String getColumnName(HttpServletRequest request, QueryBy<?> queryBy) {
	int columnOrder = Integer.parseInt(request.getParameter("order[0][column]"));
	String columnName = queryBy.getColumnName(columnOrder);

	return columnName;
    }

    private String searchBy(HttpServletRequest request) {
	String parameter = request.getParameter("search[value]");
	if (parameter.isEmpty()) {
	    return "";
	}
	return parameter.toUpperCase();
    }

    private Direction orderBy(HttpServletRequest request) {
	String order = request.getParameter("order[0][dir]");

	if (order.equalsIgnoreCase("desc")) {
	    return Sort.Direction.DESC;
	}

	return Sort.Direction.ASC;
    }

    private int getCurrentPage(int start, int length) {
	// - 1 | 2 | 3
	// 0-9|10-19|20-29
	return start / length;
    }
}