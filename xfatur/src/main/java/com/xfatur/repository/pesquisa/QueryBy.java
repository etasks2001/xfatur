package com.xfatur.repository.pesquisa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xfatur.model.produto.ClassificacaoFiscal;

public interface QueryBy {
    public Page<ClassificacaoFiscal> execute(String search, Pageable pageable, String column);
}
