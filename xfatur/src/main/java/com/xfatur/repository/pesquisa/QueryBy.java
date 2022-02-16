package com.xfatur.repository.pesquisa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryBy<T> {
	public Page<T> execute(String search, Pageable pageable, String column);

	public String getColumnName(int index);
}
