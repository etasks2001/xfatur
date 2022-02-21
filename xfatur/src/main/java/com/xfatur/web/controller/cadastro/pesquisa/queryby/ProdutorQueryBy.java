package com.xfatur.web.controller.cadastro.pesquisa.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.Produtor;
import com.xfatur.service.produto.ProdutorService;

@Component(value = "produtorqueryby")
public class ProdutorQueryBy implements QueryBy<Produtor> {

    private static final String[] COLUMNS = new String[] { "id", "descricao" };
    @Autowired
    private ProdutorService service;

    @Override
    public Page<Produtor> execute(String search, Pageable pageable, String column) {
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
