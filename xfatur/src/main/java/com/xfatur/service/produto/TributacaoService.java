package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.TributacaoIdNotFoundException;
import com.xfatur.model.produto.Tributacao;
import com.xfatur.repository.cadastro.TributacaoRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.TributacaoView;
import com.xfatur.validation.dto.cadastro.TributacaoDTO;

@Service
@Transactional(readOnly = true)
public class TributacaoService {
    @Autowired
    private TributacaoRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = false)
    public Tributacao save(TributacaoDTO tributacao) {
	return repository.save(mapper.toModel(tributacao));
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public TributacaoDTO findById(Integer id) {
	Optional<Tributacao> found = repository.findById(id);
	if (found.isPresent()) {
	    return mapper.toDto(found.get());

	}
	throw new TributacaoIdNotFoundException("Código da Tributação não encontrado");
    }

    public Page<TributacaoView> findByDescricao(String descricao, Pageable pageabel) {
	return repository.findByDescricao(descricao, pageabel);
    }

    public Boolean hasCodigo(Integer id, String codigo) {
	return repository.hasCodigo(id, codigo);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    @Transactional(readOnly = false)
    public void update(TributacaoDTO dto) {
	repository.update(dto.getId(), dto.getCodigo(), dto.getDescricao());
    }

}
