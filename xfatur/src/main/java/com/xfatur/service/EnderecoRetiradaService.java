package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.EnderecoRetirada;
import com.xfatur.repository.EnderecoRetiradaRepository;

@Service
@Transactional(readOnly = true)
public class EnderecoRetiradaService {

    @Autowired
    private EnderecoRetiradaRepository repository;

    @Transactional(readOnly = false)
    public EnderecoRetirada save(EnderecoRetirada retirada) {

	EnderecoRetirada saved = repository.save(retirada);

	return saved;
    }

}
