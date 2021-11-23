package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.ClassificacaoFiscalIdNotFoundException;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.repository.produto.ClassificacaoFiscalRepository;

@Service
public class ClassificacaoFiscalService {

    @Autowired
    private ClassificacaoFiscalRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

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

}
