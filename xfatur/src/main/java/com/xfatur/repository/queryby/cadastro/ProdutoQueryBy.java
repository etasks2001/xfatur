package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.ProdutoView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.ProdutoService;

@Component(value = "produtoqueryby")
public class ProdutoQueryBy extends QueryByAbstract<ProdutoView> {

    @Autowired
    private ProdutoService service;

    @Override
    public Page<ProdutoView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
