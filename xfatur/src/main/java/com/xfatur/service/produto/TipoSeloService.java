package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.TipoSeloIdNotFoundException;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.repository.produto.TipoSeloRepository;

@Service
@Transactional(readOnly = true)
public class TipoSeloService {
    @Autowired
    private TipoSeloRepository repository;

    @Transactional(readOnly = false)
    public TipoSelo save(TipoSelo tributacao) {
	return repository.save(tributacao);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public TipoSelo findById(Integer id) {

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
