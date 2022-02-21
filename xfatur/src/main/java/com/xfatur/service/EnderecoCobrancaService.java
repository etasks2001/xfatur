package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.test.EnderecoCobranca;
import com.xfatur.repository.EnderecoCobrancaRepository;

@Service
@Transactional(readOnly = true)
public class EnderecoCobrancaService {

    @Autowired
    private EnderecoCobrancaRepository repository;

    @Transactional(readOnly = false)
    public EnderecoCobranca save(EnderecoCobranca enderecoCobranca) {
	return repository.save(enderecoCobranca);

    }

}
