package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.exception.NaturezaJuridicaException;
import com.xfatur.exception.NaturezaJuridicaIdNotFoundException;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.repository.NaturezaJuridicaRepository;

@Service
public class NaturezaJuridicaService {

    @Autowired
    private NaturezaJuridicaRepository repository;

    public NaturezaJuridica save(NaturezaJuridica naturezaJuridica) {
	try {
	    NaturezaJuridica saved = repository.save(naturezaJuridica);

	    return saved;
	} catch (DataIntegrityViolationException e) {
	    throw new NaturezaJuridicaException("Descrição já cadastrada");
	}
    }

    public Boolean delete(Integer id) {
	Optional<NaturezaJuridica> naturezaJuridica = repository.findById(id);
	if (naturezaJuridica.isPresent()) {
	    repository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;
    }

    public List<NaturezaJuridica> buscaPorDescricao(String descricao) {
	List<NaturezaJuridica> queryByDescricao = repository.buscaPorDescricao(descricao);

	return queryByDescricao;
    }

    public NaturezaJuridica findById(int id) {
	Optional<NaturezaJuridica> findById = repository.findById(id);
	if (findById.isPresent()) {
	    return findById.get();
	}
	throw new NaturezaJuridicaIdNotFoundException("Natureza Jurídica não encontrada");
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }
}