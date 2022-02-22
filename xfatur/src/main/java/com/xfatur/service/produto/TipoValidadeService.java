package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.TipoValidadeDTO;
import com.xfatur.exception.TipoValidadeIdNotFoundException;
import com.xfatur.model.produto.TipoValidade;
import com.xfatur.repository.produto.TipoValidadeRepository;

@Service
@Transactional(readOnly = true)
public class TipoValidadeService {

    @Autowired
    private TipoValidadeRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findByIdDescricao(String descricao) {
	return repository.findByIdDescricao(descricao);
    }

    @Transactional(readOnly = false)
    public TipoValidade save(TipoValidade regiaoProdutora) {
	return repository.save(regiaoProdutora);
    }

    public TipoValidade findById(int id) {
	Optional<TipoValidade> regiaoProdutora = repository.findById(id);
	if (regiaoProdutora.isPresent()) {
	    return regiaoProdutora.get();
	}

	throw new TipoValidadeIdNotFoundException("Código do Tipo de Validade não encontrado");
    }

    public Page<TipoValidade> findByDescricao(String descricao, Pageable pageable) {
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
