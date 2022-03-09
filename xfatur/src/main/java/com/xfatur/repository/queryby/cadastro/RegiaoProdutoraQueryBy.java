package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.RegiaoProdutoraView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.RegiaoProdutoraService;

@Component(value = "regiaoprodutoraqueryby")
public class RegiaoProdutoraQueryBy extends QueryByAbstract<RegiaoProdutoraView> {

    @Autowired
    private RegiaoProdutoraService service;

    @Override
    public Page<RegiaoProdutoraView> execute(String search, Pageable pageable, String column) {
	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
