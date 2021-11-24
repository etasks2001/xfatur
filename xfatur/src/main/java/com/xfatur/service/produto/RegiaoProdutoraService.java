package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.RegiaoProdutoraIdNotFoundException;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.repository.produto.RegiaoProdutoraRepository;

@Service
public class RegiaoProdutoraService {

    @Autowired
    private RegiaoProdutoraRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findByIdDescricao(String descricao) {
	return repository.findByIdDescricao(descricao);
    }

    public RegiaoProdutora save(RegiaoProdutora regiaoProdutora) {
	return repository.save(regiaoProdutora);
    }

    public RegiaoProdutora findById(int id) {
	Optional<RegiaoProdutora> regiaoProdutora = repository.findById(id);
	if (regiaoProdutora.isPresent()) {
	    return regiaoProdutora.get();
	}

	throw new RegiaoProdutoraIdNotFoundException("Código da Região Produtora não encontrado");
    }

    public List<RegiaoProdutora> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
