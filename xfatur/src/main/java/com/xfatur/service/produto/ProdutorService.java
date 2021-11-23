package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.ProdutorIdNotFoundException;
import com.xfatur.model.produto.Produtor;
import com.xfatur.repository.produto.ProdutorRepository;

@Service
public class ProdutorService {

    @Autowired
    private ProdutorRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findByIdDescricao(String descricao) {
	return repository.findByIdDescricao(descricao);
    }

    public Produtor save(Produtor produtor) {
	return repository.save(produtor);
    }

    public Produtor findById(int id) {
	Optional<Produtor> produtor = repository.findById(id);
	if (produtor.isPresent()) {
	    return produtor.get();
	}

	throw new ProdutorIdNotFoundException("Código do Produtor não encontrado");
    }

    public List<Produtor> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
