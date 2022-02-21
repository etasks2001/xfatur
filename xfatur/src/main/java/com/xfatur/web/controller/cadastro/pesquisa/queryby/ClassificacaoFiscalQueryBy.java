package com.xfatur.web.controller.cadastro.pesquisa.queryby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.service.produto.ClassificacaoFiscalService;

@Component(value = "classificacaofiscalqueryby")
public class ClassificacaoFiscalQueryBy implements QueryBy<ClassificacaoFiscal> {

	private static final String[] COLUMNS = new String[] { "id", "ncm", "descricao" };
	@Autowired
	private ClassificacaoFiscalService service;

	@Override
	public Page<ClassificacaoFiscal> execute(String search, Pageable pageable, String column) {
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

	@Override
	public String getColumnName(int index) {
		return COLUMNS[index];
	}

}
