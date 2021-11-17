package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.exception.RamoAtividadeDescricaoException;
import com.xfatur.exception.RamoAtividadeIdNotFoundException;
import com.xfatur.model.RamoAtividade;
import com.xfatur.repository.RamoAtividadeRepository;

@Service
public class RamoAtividadeService {

    @Autowired
    private RamoAtividadeRepository repository;

    public RamoAtividade save(RamoAtividade ramoAtividade) {
	try {
	    RamoAtividade saved = repository.save(ramoAtividade);

	    return saved;
	} catch (DataIntegrityViolationException e) {
	    throw new RamoAtividadeDescricaoException("Descrição já cadastrada");
	}
    }

    public Boolean delete(Integer id) {
	Optional<RamoAtividade> ramoAtividade = repository.findById(id);
	if (ramoAtividade.isPresent()) {
	    repository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;
    }

    public List<RamoAtividade> buscaPorDescricao(String descricao) {
	List<RamoAtividade> queryByDescricao = repository.buscaPorDescricao(descricao);

	return queryByDescricao;
    }

    public RamoAtividade findById(int id) {
	Optional<RamoAtividade> findById = repository.findById(id);
	if (findById.isPresent()) {
	    return findById.get();
	}
	throw new RamoAtividadeIdNotFoundException("Ramo de Atividade não encontrado");
    }
}