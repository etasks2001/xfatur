package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.model.Entrega;
import com.xfatur.repository.EntregaRepository;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository repository;

    public Entrega save(Entrega entrega) {
	Entrega saved = repository.save(entrega);
	return saved;
    }

    public void deleteById(Integer id) {
	if (repository.existsById(id)) {
	    repository.deleteById(id);
	}

    }

}
