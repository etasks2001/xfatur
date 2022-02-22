package com.xfatur.web.controller.cadastro.pesquisa.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.Pais;
import com.xfatur.service.produto.PaisService;

@Component(value = "paisqueryby")
public class PaisQueryBy implements QueryBy<Pais> {

    private static final String[] COLUMNS = new String[] { "id", "nome", "sigla", "origem", "codigoBacen" };

    @Autowired
    private PaisService service;

    @Override
    public Page<Pais> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("nome")) {
	    return service.findByNome(search, pageable);
	}
	return Page.empty();
    }

    @Override
    public String getColumnName(int index) {
	return COLUMNS[index];
    }

}
