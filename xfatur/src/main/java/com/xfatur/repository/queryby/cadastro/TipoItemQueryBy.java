package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.TipoItemView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.TipoItemService;

@Component(value = "tipoitemqueryby")
public class TipoItemQueryBy extends QueryByAbstract<TipoItemView> {

    @Autowired
    private TipoItemService service;

    @Override
    public Page<TipoItemView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("codigo")) {
	    return service.findByCodigo(search, pageable);
	} else if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
