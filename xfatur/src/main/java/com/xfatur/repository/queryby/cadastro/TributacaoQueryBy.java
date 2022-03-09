package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.TributacaoView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.TributacaoService;

@Component(value = "tributacaoqueryby")
public class TributacaoQueryBy extends QueryByAbstract<TributacaoView> {

    @Autowired
    private TributacaoService service;

    @Override
    public Page<TributacaoView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
