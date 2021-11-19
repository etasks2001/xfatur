package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.model.EnderecoRetirada;
import com.xfatur.repository.EnderecoRetiradaRepository;

@Service
public class EnderecoRetiradaService {

    @Autowired
    private EnderecoRetiradaRepository repository;

    public EnderecoRetirada save(EnderecoRetirada retirada) {

	EnderecoRetirada saved = repository.save(retirada);

	return saved;
    }

}
