package com.xfatur.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.xfatur.model.security.PerfilTipo;
import com.xfatur.service.security.UsuarioService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String FINANCEIRO = PerfilTipo.FINANCEIRO.getDesc();
    private static final String FATURAMENTO = PerfilTipo.FATURAMENTO.getDesc();
    private static final String FISCAL = PerfilTipo.FISCAL.getDesc();

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	http.authorizeRequests()

		.antMatchers("/", "/login", "/webjars/**", "/css/**", "/js/**", "/image/**", "/u/p/**", "/expired")

		.permitAll()

		.antMatchers("/home").hasAnyAuthority(FINANCEIRO, FATURAMENTO, FISCAL)

		// Acessos privados admin
		.antMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(FATURAMENTO, FISCAL)

		.antMatchers("/u/**").hasAuthority(FINANCEIRO)

		// Acessos privados admin
		.antMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(FATURAMENTO, FINANCEIRO)

		.antMatchers("/especialidades/titulo").hasAnyAuthority(FATURAMENTO, FINANCEIRO, FISCAL)

		.antMatchers("/especialidades/**").hasAuthority(FINANCEIRO)

		// Acessos privados pacientes
		.antMatchers("/pacientes/**").hasAuthority(FISCAL)

		// Acessos privados médicos
		.antMatchers("/medicos/especialidade/titulo/*").hasAnyAuthority(FATURAMENTO, FISCAL).antMatchers("/medicos/dados", "/medicos/salvar", "/medicos/editar")
		.hasAnyAuthority(FATURAMENTO, FINANCEIRO)

		.antMatchers("/medicos/**").hasAuthority(FATURAMENTO)

		.anyRequest()

		.authenticated()

		.and()

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		.formLogin()

		.loginPage("/login")

		.defaultSuccessUrl("/home", true)

		.failureUrl("/login-error")

		.permitAll()

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		.and()

		.logout()

		.logoutSuccessUrl("/")

		.deleteCookies("JSESSIONID")

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		.and()

		.exceptionHandling()

		.accessDeniedPage("/acesso-negado")

		.and()

		.rememberMe();

	http.sessionManagement().maximumSessions(1).expiredUrl("/expired").maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry());

	http.sessionManagement().sessionFixation().newSession().sessionAuthenticationStrategy(sessionAuthenticationStrategy());

    }

    /**
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     * Para bloquear o primeiro login
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SessionRegistry sessionRegistry() {

	return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {

	return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    /**
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     * Para invalidar a sessão do primeiro login
     */

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
	return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

}