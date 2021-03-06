package com.xfatur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.thymeleaf.context.Context;

import com.xfatur.service.ListaPreco;

@Configuration
public class Config {

    @Bean
    @SessionScope
    public ListaPreco sessionListaPreco() {
	return new ListaPreco();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
	CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	loggingFilter.setIncludeClientInfo(true);
	loggingFilter.setIncludeQueryString(true);
	loggingFilter.setIncludePayload(true);
	loggingFilter.setMaxPayloadLength(64000);

	loggingFilter.setIncludeHeaders(true);

	return loggingFilter;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {

	return new BCryptPasswordEncoder();
    }

    @Bean
    public Context getContextThymeLeaf() {
	return new Context();
    }
}
