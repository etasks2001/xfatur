package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.TipoSeloIdNotFoundException;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.repository.produto.TipoSeloRepository;

@Service
public class TipoSeloService {
    @Autowired
    private TipoSeloRepository repository;

    public TipoSelo save(TipoSelo tributacao) {
	return repository.save(tributacao);
    }

    public String findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(String id) {
	repository.deleteById(id);
    }

    public TipoSelo findById(String id) {
	Optional<TipoSelo> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();

	}
	throw new TipoSeloIdNotFoundException("Código do Tipo de Selo não encontrado");
    }

    public List<TipoSelo> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
