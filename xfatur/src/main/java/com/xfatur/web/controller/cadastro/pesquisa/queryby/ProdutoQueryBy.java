package com.xfatur.web.controller.cadastro.pesquisa.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.Produto;
import com.xfatur.service.produto.ProdutoService;

@Component(value = "produtoqueryby")
public class ProdutoQueryBy implements QueryBy<Produto> {

    private static final String[] COLUMNS = new String[] { "id", "codigoProduto", "descricao" };
    @Autowired
    private ProdutoService service;

    @Override
    public Page<Produto> execute(String search, Pageable pageable, String column) {
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
