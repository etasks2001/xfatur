package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.exception.ClassificacaoFiscalIdNotFoundException;
import com.xfatur.mappers.ClassificacaoFiscalMapper;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.repository.produto.ClassificacaoFiscalRepository;

@Service
@Transactional(readOnly = true)
public class ClassificacaoFiscalService {

    @Autowired
    private ClassificacaoFiscalRepository repository;

    @Autowired
    private ClassificacaoFiscalMapper mapper;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public ClassificacaoFiscal save(ClassificacaoFiscalDTO dto) {
	return repository.save(mapper.toModel(dto));
    }

    @Transactional(readOnly = false)
    public ClassificacaoFiscal save(ClassificacaoFiscal classificacaoFiscal) {
	return repository.save(classificacaoFiscal);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public ClassificacaoFiscal findById(Integer id) {
	Optional<ClassificacaoFiscal> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();
	}
	throw new ClassificacaoFiscalIdNotFoundException("Código da Classificação Fiscal não encontrado.");

    }

    public List<ClassificacaoFiscal> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);

    }

    public Boolean hasDescricao(String descricao) {
	return repository.hasDescricao(descricao);

    }

    public Boolean hasNcm(String ncm) {
	return repository.hasNcm(ncm);
    }

    public Page<ClassificacaoFiscal> findAll(Pageable pageable) {
	return repository.findAll(pageable);
    }

    public Page<ClassificacaoFiscal> findByDescricao(String search, Pageable pageable) {
	return repository.findByDescricao(search, pageable);
    }

    public Page<ClassificacaoFiscal> findByNcm(String search, Pageable pageable) {
	return repository.findByNcm(search, pageable);
    }

}
