package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.TipoValidadeIdNotFoundException;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.repository.cadastro.TipoValidadeRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.TipoValidadeView;
import com.xfatur.validation.dto.cadastro.TipoValidadeDTO;

@Service
@Transactional(readOnly = true)
public class TipoValidadeService {

    @Autowired
    private TipoValidadeRepository repository;
    @Autowired
    private ModelMapper mapper;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findByIdDescricao(String descricao) {
	return repository.findByIdDescricao(descricao);
    }

    @Transactional(readOnly = false)
    public TipoValidade save(TipoValidadeDTO regiaoProdutora) {
	return repository.save(mapper.toModel(regiaoProdutora));
    }

    public TipoValidadeDTO findById(int id) {
	Optional<TipoValidade> regiaoProdutora = repository.findById(id);
	if (regiaoProdutora.isPresent()) {
	    return mapper.toDto(regiaoProdutora.get());
	}

	throw new TipoValidadeIdNotFoundException("Código do Tipo de Validade não encontrado");
    }

    public Page<TipoValidadeView> findByDescricao(String descricao, Pageable pageable) {
	return repository.findByDescricao(descricao, pageable);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    @Transactional(readOnly = false)
    public void update(TipoValidadeDTO dto) {
	repository.update(dto.getId(), dto.getDescricao());

    }

}
