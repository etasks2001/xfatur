package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.ProdutorView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.ProdutorService;

@Component(value = "produtorqueryby")
public class ProdutorQueryBy extends QueryByAbstract<ProdutorView> {

    @Autowired
    private ProdutorService service;

    @Override
    public Page<ProdutorView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
