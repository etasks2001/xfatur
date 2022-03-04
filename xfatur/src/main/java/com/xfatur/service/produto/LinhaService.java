package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.LinhaIdNotFoundException;
import com.xfatur.model.produto.Linha;
import com.xfatur.repository.cadastro.LinhaRepository;
import com.xfatur.validation.dto.cadastro.LinhaDTO;

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

    public Page<Linha> findByDescricao(String descricao, Pageable pageable) {
	return repository.findByDescricao(descricao, pageable);
    }

    @Transactional(readOnly = false)
    public void update(LinhaDTO dto) {
	repository.update(dto.getId(), dto.getDescricao(), dto.getTipo());
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }
}