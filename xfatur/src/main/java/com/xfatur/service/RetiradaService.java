package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.model.Retirada;
import com.xfatur.repository.RetiradaRepository;

@Service
public class RetiradaService {

    @Autowired
    private RetiradaRepository repository;

    public Retirada save(Retirada retirada) {

	Retirada saved = repository.save(retirada);

	return saved;
    }

}
