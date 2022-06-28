package com.xfatur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

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
	loggingFilter.setBeforeMessagePrefix("Before>>>");
	loggingFilter.setAfterMessagePrefix("After>>>");

	return loggingFilter;
    }

}
