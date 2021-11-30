package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.SeloDuplicadoRuntimeException;
import com.xfatur.exception.SeloIdNotFoundException;
import com.xfatur.model.Selo;
import com.xfatur.repository.SeloRepository;
import com.xfatur.util.Util;

@Service
public class SeloService {

    @Autowired
    private SeloRepository repository;

    public Selo save(Selo selo) {
	try {
	    return repository.save(selo);
	} catch (Exception e) {
	    throw new SeloDuplicadoRuntimeException(Util.extractContraintMessage(e));
	}
    }

    public Selo findById(Integer id) {
	Optional<Selo> found = repository.findById(id);

	if (found.isPresent()) {
	    return found.get();

	}

	throw new SeloIdNotFoundException("Selo não encontrado");
    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public List<Selo> findAllByNumeroGuiaContains(String numeroGuia) {
	return repository.findAllByNumeroGuiaContains(numeroGuia);
    }

}
