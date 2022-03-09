package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.OrigemView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.OrigemService;

@Component(value = "origemqueryby")
public class OrigemQueryBy extends QueryByAbstract<OrigemView> {

    @Autowired
    private OrigemService service;

    @Override
    public Page<OrigemView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("descricao")) {
	    return service.findByNome(search, pageable);
	}
	return Page.empty();
    }

}
