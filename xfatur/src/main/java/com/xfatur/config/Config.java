package com.xfatur.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@ComponentScan(basePackages = "com.xfatur.config")
public class Config {
    @Value("${fieldsname.path}")
    private String messagesBasename;

    @Bean
    public MessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setFallbackToSystemLocale(false);
	messageSource.setBasenames("file:" + messagesBasename);
	System.out.println(messagesBasename);
	return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
	SessionLocaleResolver slr = new SessionLocaleResolver();
	slr.setDefaultLocale(Locale.US);
	return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
	LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	lci.setParamName("lang");
	return lci;
    }

    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(localeChangeInterceptor());
    }

}
