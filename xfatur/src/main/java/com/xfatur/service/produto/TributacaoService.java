package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.TributacaoIdNotFoundException;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.repository.produto.TributacaoRepository;

@Service
@Transactional(readOnly = true)
public class TributacaoService {
    @Autowired
    private TributacaoRepository repository;

    @Transactional(readOnly = false)
    public Tributacao save(Tributacao tributacao) {
	return repository.save(tributacao);
    }

    public String findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(String id) {
	repository.deleteById(id);
    }

    public Tributacao findById(String id) {
	Optional<Tributacao> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();

	}
	throw new TributacaoIdNotFoundException("Código da Tributação não encontrado");
    }

    public List<Tributacao> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
