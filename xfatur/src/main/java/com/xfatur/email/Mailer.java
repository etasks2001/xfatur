package com.xfatur.email;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer implements Runnable {

    public static void main(String[] args) throws InterruptedException {
	Mailer mailer = new Mailer();
	Thread thread = new Thread(mailer);
	thread.setName("E-mail sender.");

	thread.start();
    }

    @Override
    public void run() {
	try {

	    Properties smtpProperties = System.getProperties();

	    smtpProperties.load(new FileInputStream(new File("c:/smtp.properties")));

	    String to = smtpProperties.getProperty("to");
	    String from = smtpProperties.getProperty("from");
	    String mensagem = "Message";
	    String assunto = "Assunto";
	    String userName = smtpProperties.getProperty("from");
	    String password = smtpProperties.getProperty("password");

	    Properties props = System.getProperties();

	    props.put("mail.smtp.ssl.enabled", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	    props.put("mail.smtp.socketFactory.class", "com.xfatur.email.AlwaysTrustSSLContextFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.host", "email-ssl.com.br");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.socketFactory.fallback", "false");

	    Session session = Session.getInstance(props, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(userName, password);
		}
	    });

	    session.setDebug(true);

	    MimeMessage message = new MimeMessage(session);

	    message.setFrom(from);
	    message.setRecipients(Message.RecipientType.TO, to);
	    message.setSubject(assunto);

	    MimeMultipart multiPart = new MimeMultipart();

	    MimeBodyPart body = new MimeBodyPart();

	    body.setText(mensagem);

	    String[] files = new String[] { "c:/pws.txt", "c:/pws2.txt", "c:/soap.xml" };
	    for (String file : files) {
		MimeBodyPart attach = new MimeBodyPart();
		attach.attachFile(file);
		multiPart.addBodyPart(attach);
	    }

	    multiPart.addBodyPart(body);

	    message.setContent(multiPart);
	    message.setSentDate(new Date());

	    Transport.send(message);

	    System.out.println("enviado");

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}