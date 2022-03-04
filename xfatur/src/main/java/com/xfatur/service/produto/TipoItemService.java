package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.TipoIdNotFoundException;
import com.xfatur.model.produto.TipoItem;
import com.xfatur.repository.cadastro.TipoItemRepository;
import com.xfatur.validation.dto.cadastro.TipoItemDTO;

@Service
@Transactional(readOnly = true)
public class TipoItemService {
    @Autowired
    private TipoItemRepository repository;

    @Transactional(readOnly = false)
    public TipoItem save(TipoItem tributacao) {
	return repository.save(tributacao);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public void deleteById(Integer id) {

	repository.deleteById(id);

    }

    public TipoItem findById(Integer id) {
	Optional<TipoItem> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();

	}
	throw new TipoIdNotFoundException("Código do Tipo do Item não encontrado");
    }

    public List<TipoItem> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    public Boolean hasCodigo(Integer id, String codigo) {
	return repository.hasCodigo(id, codigo);
    }

    public Page<TipoItem> findByCodigo(String search, Pageable pageable) {
	return repository.findByCodigo(search, pageable);
    }

    public Page<TipoItem> findByDescricao(String search, Pageable pageable) {
	return repository.findByDescricao(search, pageable);
    }

    @Transactional(readOnly = false)
    public void update(TipoItemDTO dto) {
	repository.update(dto.getId(), dto.getCodigo(), dto.getDescricao());
    }

}
