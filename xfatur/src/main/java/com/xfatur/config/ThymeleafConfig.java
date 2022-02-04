package com.xfatur.config;

import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

//@Configuration
public class ThymeleafConfig {

//    @Bean
    public ServletContextTemplateResolver templateResolver() {
	final ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(null);
	resolver.setPrefix("/WEB-INF/templates/");
	resolver.setTemplateMode("HTML5");
	resolver.setCharacterEncoding("UTF-8");
	resolver.setOrder(2);
//	resolver.setCacheable(!appSettings.isDebugMode() || !appSettings.isOneBoxMode());
	return resolver;
    }

}