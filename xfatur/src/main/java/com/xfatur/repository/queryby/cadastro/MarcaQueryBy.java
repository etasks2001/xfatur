package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.MarcaView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.MarcaService;

@Component(value = "marcaqueryby")
public class MarcaQueryBy extends QueryByAbstract<MarcaView> {

    @Autowired
    private MarcaService service;

    @Override
    public Page<MarcaView> executeOptions(String search, Pageable pageable, String column) {
	if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }

}
