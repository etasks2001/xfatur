package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.UnidadeIdNotFoundException;
import com.xfatur.model.produto.Unidade;
import com.xfatur.repository.cadastro.UnidadeRepository;
import com.xfatur.repository.projections.cadastro.UnidadeView;
import com.xfatur.validation.dto.cadastro.UnidadeDTO;

@Service
@Transactional(readOnly = true)
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    public Unidade findById(Integer id) {
	Optional<Unidade> unidade = repository.findById(id);
	if (unidade.isPresent()) {
	    return unidade.get();
	}
	throw new UnidadeIdNotFoundException("Código da Unidade não encontrado");
    }

    public Integer findIdByAbreviacao(String abreviacao) {
	return repository.findIdByAbreviacao(abreviacao);
    }

    @Transactional(readOnly = false)
    public Unidade save(Unidade unidade) {
	return repository.save(unidade);
    }

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Page<UnidadeView> findByDescricao(String descricao, Pageable pegeable) {
	return repository.findByDescricao(descricao, pegeable);
    }

    public Boolean hasAbreviacao(Integer id, String abreviacao) {
	return repository.hasAbreviacao(id, abreviacao);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    public void updte(UnidadeDTO dto) {
	repository.update(dto.getId(), dto.getDescricao(), dto.getAbreviacao());
    }

    public Page<UnidadeView> findByAbreviacao(String search, Pageable pageable) {
	return repository.findByAbreviacao(search, pageable);
    }

    @Transactional(readOnly = false)
    public void update(UnidadeDTO dto) {
	repository.update(dto.getId(), dto.getDescricao(), dto.getAbreviacao());
    }

}