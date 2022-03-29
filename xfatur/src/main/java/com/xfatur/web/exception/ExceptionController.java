package com.xfatur.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.xfatur.exception.ClassificacaoFiscalCodigoNaoEncontrado;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ClassificacaoFiscalCodigoNaoEncontrado.class)
    public ModelAndView usuarioNaoEncontradoException(ClassificacaoFiscalCodigoNaoEncontrado e) {

	ModelAndView model = new ModelAndView("error");
	model.addObject("status", "404");
	model.addObject("error", "Operação não pode ser realizada.");
	model.addObject("message", e.getMessage());

	return model;

    }
}
