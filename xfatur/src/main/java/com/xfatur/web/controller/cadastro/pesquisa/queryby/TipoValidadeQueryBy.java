package com.xfatur.web.controller.cadastro.pesquisa.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.TipoValidade;
import com.xfatur.service.produto.TipoValidadeService;

@Component(value = "tipovalidadequeryby")
public class TipoValidadeQueryBy implements QueryBy<TipoValidade> {

    private static final String[] COLUMNS = new String[] { "id", "descricao" };
    @Autowired
    private TipoValidadeService service;

    @Override
    public Page<TipoValidade> execute(String search, Pageable pageable, String column) {
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
