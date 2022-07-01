package com.xfatur.service.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private SpringTemplateEngine template;

    @Autowired
    private Context context;

    public void enviarPedidoDeRedefinicaoDeSenha(String destino, String verificador) throws MessagingException {
	try {
	    Properties properties = new Properties();
	    properties.load(new FileInputStream(new File("c:/email.properties")));

	    mailSender.setUsername(properties.getProperty("username"));
	    mailSender.setPassword(properties.getProperty("password"));

	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

	    context.setVariable("verificador", verificador);

	    String html = template.process("email/confirmacao", context);

	    helper.setTo(destino);
	    helper.setText(html, true);
	    helper.setSubject("Redefinição de cadastro");
	    helper.setFrom(properties.getProperty("from"));

	    helper.addInline("logo", new ClassPathResource("/static/image/spring-security.png"));

	    mailSender.send(message);
	} catch (MailException | IOException e) {
	    throw new MessagingException(e.getMessage());
	}

    }
}