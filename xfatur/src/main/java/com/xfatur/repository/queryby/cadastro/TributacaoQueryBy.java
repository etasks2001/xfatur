package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.Tributacao;
import com.xfatur.repository.queryby.QueryBy;
import com.xfatur.service.produto.TributacaoService;

@Component(value = "tributacaoqueryby")
public class TributacaoQueryBy implements QueryBy<Tributacao> {

    private static final String[] COLUMNS = new String[] { "id", "codigo", "descricao" };

    @Autowired
    private TributacaoService service;

    @Override
    public Page<Tributacao> execute(String search, Pageable pageable, String column) {
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
