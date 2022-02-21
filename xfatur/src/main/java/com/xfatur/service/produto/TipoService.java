package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.TipoIdNotFoundException;
import com.xfatur.model.produto.Tipo;
import com.xfatur.repository.produto.TipoRepository;

@Service
public class TipoService {
    @Autowired
    private TipoRepository repository;

    public Tipo save(Tipo tributacao) {
	return repository.save(tributacao);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(Integer id) {

	repository.deleteById(id);

    }

    public Tipo findById(Integer id) {
	Optional<Tipo> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();

	}
	throw new TipoIdNotFoundException("Código do Tipo não encontrado");
    }

    public List<Tipo> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
