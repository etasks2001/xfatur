package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.TipoValidadeIdNotFoundException;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.repository.produto.TipoValidadeRepository;

@Service
public class TipoValidadeService {

    @Autowired
    private TipoValidadeRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findByIdDescricao(String descricao) {
	return repository.findByIdDescricao(descricao);
    }

    public TipoValidade save(TipoValidade regiaoProdutora) {
	return repository.save(regiaoProdutora);
    }

    public TipoValidade findById(int id) {
	Optional<TipoValidade> regiaoProdutora = repository.findById(id);
	if (regiaoProdutora.isPresent()) {
	    return regiaoProdutora.get();
	}

	throw new TipoValidadeIdNotFoundException("Código do Tipo de Validade não encontrado");
    }

    public List<TipoValidade> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
