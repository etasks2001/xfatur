package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.TipoSeloIdNotFoundException;
import com.xfatur.model.produto.TipoSelo;
import com.xfatur.repository.cadastro.TipoSeloRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.TipoSeloView;
import com.xfatur.validation.dto.cadastro.TipoSeloDTO;

@Service
@Transactional(readOnly = true)
public class TipoSeloService {
    @Autowired
    private TipoSeloRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = false)
    public TipoSeloDTO save(TipoSeloDTO tributacao) {
	TipoSelo saved = repository.save(mapper.toModel(tributacao));
	return mapper.toDto(saved);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public TipoSeloDTO findById(Integer id) {

	Optional<TipoSelo> found = repository.findById(id);
	if (found.isPresent()) {
	    return mapper.toDto(found.get());

	}
	throw new TipoSeloIdNotFoundException("Código do Tipo de Selo não encontrado");
    }

    public Page<TipoSeloView> findByDescricao(String descricao, Pageable pageable) {
	return repository.findByDescricao(descricao, pageable);
    }

    public Boolean hasCodigo(Integer id, String codigo) {
	return repository.hasCodigo(id, codigo);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    @Transactional(readOnly = false)
    public void update(TipoSeloDTO dto) {
	repository.update(dto.getId(), dto.getCodigo(), dto.getDescricao());
    }

}
