package com.xfatur.service.produto;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.OrigemDTO;
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

	public Page<Origem> findByDescricao(String descricao, Pageable pageable) {
		return repository.findByDescricao(descricao, pageable);
	}

	public Boolean hasDescricao(Integer id, String descricao) {

		return repository.hasDescricao(id, descricao);
	}

	public Boolean hasId(Integer id) {
		return repository.hasId(id);
	}

	@Transactional(readOnly = false)
	public void update(@Valid OrigemDTO dto) {
		repository.update(dto.getId(), dto.getDescricao());

	}

}
