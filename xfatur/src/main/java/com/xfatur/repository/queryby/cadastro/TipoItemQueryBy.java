package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.TipoItemView;
import com.xfatur.repository.queryby.QueryBy;
import com.xfatur.service.produto.TipoItemService;

@Component(value = "tipoitemqueryby")
public class TipoItemQueryBy implements QueryBy<TipoItemView> {

    private static final String[] COLUMNS = new String[] { "id", "codigo", "descricao" };
    @Autowired
    private TipoItemService service;

    @Override
    public Page<TipoItemView> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}
	System.out.println(column);
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
