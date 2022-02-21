package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.LinhaIdNotFoundException;
import com.xfatur.model.produto.Linha;
import com.xfatur.repository.produto.LinhaRepository;

@Service
@Transactional(readOnly = true)
public class LinhaService {

    @Autowired
    private LinhaRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    @Transactional(readOnly = false)
    public Linha save(Linha linha) {
	return repository.save(linha);
    }

    public Linha findById(int id) {
	Optional<Linha> linha = repository.findById(id);
	if (linha.isPresent()) {
	    return linha.get();
	}

	throw new LinhaIdNotFoundException("Código da Linha não encontrado");
    }

    public List<Linha> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
