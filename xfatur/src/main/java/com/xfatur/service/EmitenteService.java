package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.EmitenteException;
import com.xfatur.exception.EmitenteIdNotFoundException;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.model.Emitente;
import com.xfatur.repository.EmitenteRepository;

@Service
@Transactional(readOnly = true)
public class EmitenteService {

    @Autowired
    private EmitenteRepository repository;

    public Emitente findById(Integer id) {
	Optional<Emitente> emitente = repository.findById(id);
	if (emitente.isPresent()) {
	    return emitente.get();
	}
	throw new EmitenteIdNotFoundException("Emitente Não encontrado");
    }

    @Transactional(readOnly = false)
    public Emitente save(Emitente emitente) {
	try {
	    Emitente saved = repository.save(emitente);

	    return saved;
	} catch (DataIntegrityViolationException e) {
	    throw new EmitenteException("CNPJ/CPF já cadastrado");
	}
    }

    public Boolean delete(Integer id) {
	Optional<Emitente> emitente = repository.findById(id);

	if (emitente.isPresent()) {
	    repository.delete(emitente.get());
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;

    }

    public Emitente findByCNPJ(String cnpj) {
	Emitente emitente = repository.findByCNPJ(cnpj);

	if (emitente != null) {
	    return emitente;
	}
	throw new EmitenteNotFoundException("Emitente Não encontrado");
    }

    public List<Emitente> buscaPorNome(String nome) {
	List<Emitente> emitentes = repository.buscaPorNome(nome);

	return emitentes;
    }
}