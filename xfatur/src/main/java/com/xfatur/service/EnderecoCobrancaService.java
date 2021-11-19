package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.model.test.EnderecoCobranca;
import com.xfatur.repository.EnderecoCobrancaRepository;

@Service
public class EnderecoCobrancaService {

    @Autowired
    private EnderecoCobrancaRepository repository;

    public EnderecoCobranca save(EnderecoCobranca enderecoCobranca) {
	return repository.save(enderecoCobranca);

    }

}
