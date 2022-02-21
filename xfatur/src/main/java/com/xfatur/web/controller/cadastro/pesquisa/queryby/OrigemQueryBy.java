package com.xfatur.web.controller.cadastro.pesquisa.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.Origem;
import com.xfatur.service.produto.OrigemService;

@Component(value = "origemqueryby")
public class OrigemQueryBy implements QueryBy<Origem> {

	private static final String[] COLUMNS = new String[] { "id", "codigo", "descricao" };

	@Autowired
	private OrigemService service;

	@Override
	public Page<Origem> execute(String search, Pageable pageable, String column) {
		if (search.trim().length() == 0) {
			return Page.empty();
		}

		if (column.equals("descricao")) {
			return service.findByDescricao(search, pageable);
		}
		return Page.empty();
	}

	@Override
	public String getColumnName(int index) {
		return COLUMNS[index];
	}

}
