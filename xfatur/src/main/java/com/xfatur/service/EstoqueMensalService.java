package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.model.EstoqueMensal;
import com.xfatur.repository.EstoqueMensalRepository;

@Service
public class EstoqueMensalService {

    @Autowired
    private EstoqueMensalRepository repository;

    public EstoqueMensal save(EstoqueMensal estoqueMensal) {
	return repository.save(estoqueMensal);

    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

}
