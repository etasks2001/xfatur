package com.xfatur.repository.queryby.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.repository.projections.cadastro.ClassificacaoFiscalView;
import com.xfatur.repository.queryby.QueryByAbstract;
import com.xfatur.service.produto.ClassificacaoFiscalService;

@Component(value = "classificacaofiscalqueryby")
public class ClassificacaoFiscalQueryBy extends QueryByAbstract<ClassificacaoFiscalView> {

    @Autowired
    private ClassificacaoFiscalService service;

    @Override
    public Page<ClassificacaoFiscalView> execute(String search, Pageable pageable, String column) {

	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	if (column.equals("ncm")) {
	    return service.findByNcm(search, pageable);
	} else if (column.equals("descricao")) {
	    return service.findByDescricao(search, pageable);
	}
	return Page.empty();
    }
}