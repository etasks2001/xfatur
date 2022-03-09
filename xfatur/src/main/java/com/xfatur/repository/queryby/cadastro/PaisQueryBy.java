package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.PaisView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.PaisService;

@Component(value = "paisqueryby")
public class PaisQueryBy extends QueryByAbstract<PaisView> {

    @Autowired
    private PaisService service;

    @Override
    public Page<PaisView> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("nome")) {
	    return service.findByNome(search, pageable);
	}
	return Page.empty();
    }

}
