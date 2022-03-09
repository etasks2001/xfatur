package com.xfatur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.xfatur.service.ListaPreco;

@Configuration
public class Config {

    @Bean
    @SessionScope
    public ListaPreco sessionListaPreco() {
	return new ListaPreco();
    }

}
