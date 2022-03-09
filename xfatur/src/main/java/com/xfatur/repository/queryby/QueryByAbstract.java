package com.xfatur.repository.queryby;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract class QueryByAbstract<T> implements QueryBy<T> {

    @Autowired
    protected ResourceBundleMessageSource messageSource;

    protected String code = String.format("pesquisa.%s.columns", getClass().getSimpleName().toLowerCase().replace("queryby", ""));

    @Override
    public String getColumnName(int index) {

	String[] split = messageSource.getMessage(code, null, Locale.ENGLISH).split(",");

	return split[index];
    }

}
