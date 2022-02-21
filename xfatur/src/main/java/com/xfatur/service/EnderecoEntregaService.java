package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.EnderecoEntrega;
import com.xfatur.repository.EnderecoEntregaRepository;

@Service
@Transactional(readOnly = true)
public class EnderecoEntregaService {
    @Autowired
    private EnderecoEntregaRepository repository;

    @Transactional(readOnly = false)
    public EnderecoEntrega save(EnderecoEntrega entrega) {
	EnderecoEntrega saved = repository.save(entrega);
	return saved;
    }

}
