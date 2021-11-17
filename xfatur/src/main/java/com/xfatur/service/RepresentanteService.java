package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.exception.RepresentanteException;
import com.xfatur.exception.RepresentanteIdNotFoundException;
import com.xfatur.exception.RepresentanteNotFoundException;
import com.xfatur.model.Representante;
import com.xfatur.repository.RepresentanteRepository;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository repository;

    public Representante save(Representante representante) {
	try {
	    Representante saved = repository.save(representante);
	    return saved;
	} catch (DataIntegrityViolationException e) {
	    throw new RepresentanteException("CNPJ/CPF já cadastrado");
	}
    }

    public Boolean delete(Integer id) {
	Optional<Representante> found = repository.findById(id);
	if (found.isPresent()) {
	    repository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;
    }

    public Representante findByCNPJCPF(String cnpjcpf) {
	Representante found = repository.findByCNPJCPF(cnpjcpf);
	if (found != null) {
	    return found;
	}
	throw new RepresentanteNotFoundException("Representante não encontrado");
    }

    public List<Representante> buscaPorNome(String nome) {
	List<Representante> representanteList = repository.buscaPorNome(nome);

	return representanteList;
    }

    public Representante findById(Integer id) {
	Optional<Representante> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();
	}

	throw new RepresentanteIdNotFoundException("Código do Representante não encontrado");
    }

}