package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.MarcaDTO;
import com.xfatur.exception.MarcaIdNotFoundException;
import com.xfatur.model.produto.Marca;
import com.xfatur.repository.produto.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repository;

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Integer findIdByDescricao(String descricao) {
		return repository.findIdByDescricao(descricao);
	}

	public Marca save(Marca marca) {
		return repository.save(marca);
	}

	public Marca findById(int id) {
		Optional<Marca> marca = repository.findById(id);
		if (marca.isPresent()) {
			return marca.get();
		}

		throw new MarcaIdNotFoundException("Código da Marca não encontrado");
	}

	public List<Marca> findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}

	@Transactional
	public void update(MarcaDTO dto) {
		repository.update(dto.getId(), dto.getDescricao());

	}

	public Page<Marca> findByDescricao(String search, Pageable pageable) {
		return repository.findByDescricao(search, pageable);
	}

	public Boolean hasDescricao(String descricao) {
		return repository.hasDescricao(descricao);
	}

	public Boolean hasDescricao(Integer id, String descricao) {
		return repository.hasDescricao(id, descricao);
	}

}
