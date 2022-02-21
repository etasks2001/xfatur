package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.UnidadeIdNotFoundException;
import com.xfatur.model.produto.Unidade;
import com.xfatur.repository.produto.UnidadeRepository;

@Service
@Transactional(readOnly = true)
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    public Unidade findById(Integer id) {
	Optional<Unidade> unidade = repository.findById(id);
	if (unidade.isPresent()) {
	    return unidade.get();
	}
	throw new UnidadeIdNotFoundException("Código da Unidade não encontrado");
    }

    public Integer findIdByAbreviacao(String abreviacao) {
	return repository.findIdByAbreviacao(abreviacao);
    }

    @Transactional(readOnly = false)
    public Unidade save(Unidade unidade) {
	return repository.save(unidade);
    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public List<Unidade> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
