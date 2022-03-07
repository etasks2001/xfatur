package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.MarcaView;
import com.xfatur.repository.queryby.QueryBy;
import com.xfatur.service.produto.MarcaService;

@Component(value = "marcaqueryby")
public class MarcaQueryBy implements QueryBy<MarcaView> {

    private static final String[] COLUMNS = new String[] { "id", "descricao" };
    @Autowired
    private MarcaService service;

    @Override
    public Page<MarcaView> execute(String search, Pageable pageable, String column) {
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
