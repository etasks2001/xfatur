package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.LinhaView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.LinhaService;

@Component(value = "linhaqueryby")
public class LinhaQueryBy extends QueryByAbstract<LinhaView> {

    @Autowired
    private LinhaService service;

    @Override
    public Page<LinhaView> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
