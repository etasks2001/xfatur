package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.UnidadeView;
import com.xfatur.repository.queryby.QueryBy;
import com.xfatur.service.produto.UnidadeService;

@Component(value = "unidadequeryby")
public class UnidadeQueryBy implements QueryBy<UnidadeView> {

    private static final String[] COLUMNS = new String[] { "id", "descricao", "abreviacao" };

    @Autowired
    private UnidadeService service;

    @Override
    public Page<UnidadeView> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}

	if (column.equals("abreviacao")) {
	    return service.findByAbreviacao(search.toLowerCase(), pageable);
	}
	return Page.empty();
    }

    @Override
    public String getColumnName(int index) {
	return COLUMNS[index];
    }

}
