package com.xfatur.repository.queryby;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class QueryByAbstract<T> implements QueryBy<T> {

    @Autowired
    protected ResourceBundleMessageSource messageSource;

    protected String code = String.format("pesquisa.%s.columns", getClass().getSimpleName().toLowerCase().replace("queryby", ""));

    @Override
    public String getColumnName(int index) {

	String[] split = messageSource.getMessage(code, null, Locale.ENGLISH).split(",");

	return split[index];
    }

    @Override
    public Page<T> execute(String search, Pageable pageable, String column) {

	if (search.trim().length() == 0) {
	    return Page.empty();
	}

	return executeOptions(search, pageable, column);
    }

    protected abstract Page<T> executeOptions(String search, Pageable pageable, String column);

}
