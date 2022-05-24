package com.xfatur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableJpaRepositories(enableDefaultTransactions = false)
public class XFaturApplication {

    public static void main(String[] args) {
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	
	SpringApplication.run(XFaturApplication.class, args);

    }

}
