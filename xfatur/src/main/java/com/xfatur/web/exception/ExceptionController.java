package com.xfatur.web.exception;

import javax.mail.SendFailedException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.xfatur.exception.ClassificacaoFiscalCodigoNaoEncontrado;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView usuarioNaoEncontradoException(UsernameNotFoundException e) {

	ModelAndView model = new ModelAndView("error");
	model.addObject("status", "404");
	model.addObject("error", "Operação não pode ser realizada.");
	model.addObject("message", e.getMessage());

	return model;
    }

    @ExceptionHandler(ClassificacaoFiscalCodigoNaoEncontrado.class)
    public ModelAndView classificacaoFiscalNaoEncontradoException(ClassificacaoFiscalCodigoNaoEncontrado e) {

	ModelAndView model = new ModelAndView("error");
	model.addObject("status", "404");
	model.addObject("error", "Operação não pode ser realizada.");
	model.addObject("message", e.getMessage());

	return model;
    }

    @ExceptionHandler(SendFailedException.class)
    public ModelAndView emailRedefinicaoDeSenhaNaoPodeSerEnviado(SendFailedException e) {
	ModelAndView model = new ModelAndView("error");

	model.addObject("status", "404");
	model.addObject("error", "E-mail não pode ser enviado.");
	model.addObject("message", e.getMessage());

	return model;
    }

}
