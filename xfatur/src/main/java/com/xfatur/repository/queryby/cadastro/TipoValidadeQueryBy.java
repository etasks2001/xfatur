package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.TipoValidadeView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.TipoValidadeService;

@Component(value = "tipovalidadequeryby")
public class TipoValidadeQueryBy extends QueryByAbstract<TipoValidadeView> {

    @Autowired
    private TipoValidadeService service;

    @Override
    public Page<TipoValidadeView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
