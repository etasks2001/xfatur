package com.xfatur.service.produto;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.OrigemIdNotFoundException;
import com.xfatur.model.produto.Origem;
import com.xfatur.repository.cadastro.OrigemRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.OrigemView;
import com.xfatur.validation.dto.cadastro.OrigemDTO;

@Service
@Transactional(readOnly = true)
public class OrigemService {

    @Autowired
    private OrigemRepository repository;

    @Autowired
    private ModelMapper mapper;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    @Transactional(readOnly = false)
    public OrigemDTO save(OrigemDTO origem) {
	Origem saved = repository.save(mapper.toModel(origem));
	return mapper.toDto(saved);
    }

    public OrigemDTO findById(int id) {
	Optional<Origem> origem = repository.findById(id);
	if (origem.isPresent()) {
	    return mapper.toDto(origem.get());
	}

	throw new OrigemIdNotFoundException("Código da Origem não encontrado");
    }

    public Page<OrigemView> findByNome(String descricao, Pageable pageable) {
	return repository.findByDescricao(descricao, pageable);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    public Boolean hasCodigo(Integer id, String codigo) {
	return repository.hasCodigo(id, codigo);
    }

    @Transactional(readOnly = false)
    public void update(@Valid OrigemDTO dto) {
	repository.update(dto.getId(), dto.getCodigo(), dto.getDescricao());

    }

}
