package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ClassificacaoFiscalIdNotFoundException;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.repository.produto.ClassificacaoFiscalRepository;
import com.xfatur.service.Servico;

@Service(value = "classificacaofiscalservice")
@Transactional(readOnly = true)
public class ClassificacaoFiscalService implements Servico {

    @Autowired
    private ClassificacaoFiscalRepository repository;

    public void deleteById(Integer id) {

	repository.deleteById(id);
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

    public Boolean hasDescricaoNotFromId(String descricao, Integer id) {
	return repository.hasDescricaoNotFromId(descricao, id);
    }

    public Boolean hasNcm(String ncm) {
	return repository.hasNcm(ncm);
    }

    public Boolean hasNcmNotFromId(String ncm, Integer id) {
	return repository.hasNcmNotFromId(ncm, id);
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

    @Transactional(readOnly = false)
    public void update(ClassificacaoFiscal model) {
	repository.update(model.getId(), model.getNcm(), model.getDescricao());

    }

}
