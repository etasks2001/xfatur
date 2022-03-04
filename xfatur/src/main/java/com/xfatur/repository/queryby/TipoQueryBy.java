package com.xfatur.repository.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.TipoItem;
import com.xfatur.service.produto.TipoItemService;

@Component(value = "tipoitemqueryby")
public class TipoQueryBy implements QueryBy<TipoItem> {

    private static final String[] COLUMNS = new String[] { "id", "ncm", "descricao" };
    @Autowired
    private TipoItemService service;

    @Override
    public Page<TipoItem> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("codigo")) {
	    return service.findByCodigo(search, pageable);
	} else if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

    @Override
    public String getColumnName(int index) {
	return COLUMNS[index];
    }

}
