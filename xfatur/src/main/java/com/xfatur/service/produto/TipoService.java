package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.TipoDTO;
import com.xfatur.exception.TipoIdNotFoundException;
import com.xfatur.model.produto.Tipo;
import com.xfatur.repository.produto.TipoRepository;

@Service
@Transactional(readOnly = true)
public class TipoService {
    @Autowired
    private TipoRepository repository;

    @Transactional(readOnly = false)
    public Tipo save(Tipo tributacao) {
	return repository.save(tributacao);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(Integer id) {

	repository.deleteById(id);

    }

    public Tipo findById(Integer id) {
	Optional<Tipo> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();

	}
	throw new TipoIdNotFoundException("Código do Tipo não encontrado");
    }

    public List<Tipo> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    public Boolean hasCodigo(Integer id, String codigo) {
	return repository.hasCodigo(id, codigo);
    }

    public Page<Tipo> findByCodigo(String search, Pageable pageable) {
	return repository.findByCodigo(search, pageable);
    }

    public Page<Tipo> findByDescricao(String search, Pageable pageable) {
	return repository.findByDescricao(search, pageable);
    }

    @Transactional(readOnly = false)
    public void update(TipoDTO dto) {
	repository.update(dto.getId(), dto.getCodigo(), dto.getDescricao());

    }

}
