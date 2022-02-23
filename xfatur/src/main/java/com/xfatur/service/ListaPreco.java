package com.xfatur.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
public class ListaPreco {

    @Bean
    @SessionScope
    public ListaPreco getListaPreco() {

	return new ListaPrecoApp();
    }
}
