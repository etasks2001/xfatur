package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.RegiaoProdutoraDTO;
import com.xfatur.exception.RegiaoProdutoraIdNotFoundException;
import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.repository.produto.RegiaoProdutoraRepository;

@Service
@Transactional(readOnly = true)
public class RegiaoProdutoraService {

	@Autowired
	private RegiaoProdutoraRepository repository;

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Integer findByIdDescricao(String descricao) {
		return repository.findByIdDescricao(descricao);
	}

	@Transactional(readOnly = false)
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

	public Page<RegiaoProdutora> findByDescricao(String descricao, Pageable pageable) {
		return repository.findByDescricao(descricao, pageable);
	}

	@Transactional(readOnly = false)
	public void update(RegiaoProdutoraDTO dto) {
		repository.update(dto.getId(), dto.getDescricao());

	}

	public Boolean hasDescricao(Integer id, String descricao) {
		return repository.hasDescricao(id, descricao);
	}

}
