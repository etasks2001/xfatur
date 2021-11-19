package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.model.EnderecoEntrega;
import com.xfatur.repository.EnderecoEntregaRepository;

@Service
public class EnderecoEntregaService {
    @Autowired
    private EnderecoEntregaRepository repository;

    public EnderecoEntrega save(EnderecoEntrega entrega) {
	EnderecoEntrega saved = repository.save(entrega);
	return saved;
    }

    public void deleteById(Integer id) {
	if (repository.existsById(id)) {
	    repository.deleteById(id);
	}

    }

}
