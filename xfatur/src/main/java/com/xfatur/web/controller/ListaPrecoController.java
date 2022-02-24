package com.xfatur.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xfatur.service.ListaPreco;

@Controller
@RequestMapping("listapreco")
public class ListaPrecoController {
    @Resource(name = "sessionListaPreco")
    private ListaPreco listaPreco;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @GetMapping
    public String openForm() {
	listaPreco.run();

	HttpSession session = httpSessionFactory.getObject();
	System.out.println(session.getId());

	return "/listapreco/listapreco";
    }
}