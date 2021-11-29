package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.EstoqueMensalDuplicadoRuntimeException;
import com.xfatur.exception.EstoqueMensalIdNotFoundException;
import com.xfatur.model.EstoqueMensal;
import com.xfatur.repository.EstoqueMensalRepository;
import com.xfatur.util.Util;

@Service
public class EstoqueMensalService {

    @Autowired
    private EstoqueMensalRepository repository;

    public EstoqueMensal save(EstoqueMensal estoqueMensal) {
	try {
	    return repository.save(estoqueMensal);
	} catch (Exception e) {
	    throw new EstoqueMensalDuplicadoRuntimeException(Util.extractContraintMessage(e));
	}

    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public EstoqueMensal findById(Integer id) {
	Optional<EstoqueMensal> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();
	}
	throw new EstoqueMensalIdNotFoundException("Estoque mensal não encontrado");
    }

    public List<EstoqueMensal> findAllByMesAno(int mes, int ano) {
	return repository.findAllByMesAno(mes, ano);
    }

}
