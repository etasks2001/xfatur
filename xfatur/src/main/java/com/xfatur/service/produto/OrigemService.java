package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.OrigemIdNotFoundException;
import com.xfatur.model.produto.Origem;
import com.xfatur.repository.produto.OrigemRepository;

@Service
public class OrigemService {

    @Autowired
    private OrigemRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public Origem save(Origem origem) {
	return repository.save(origem);
    }

    public Origem findById(int id) {
	Optional<Origem> origem = repository.findById(id);
	if (origem.isPresent()) {
	    return origem.get();
	}

	throw new OrigemIdNotFoundException("Código da Origem não encontrado");
    }

    public List<Origem> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
