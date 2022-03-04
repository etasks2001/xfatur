package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.ClassificacaoFiscalDTO;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.repository.cadastro.ClassificacaoFiscalRepository;
import com.xfatur.repository.projections.ClassificacaoFiscalView;
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
		throw new RuntimeException("Código da Classificação Fiscal não encontrado.");

	}

	public List<ClassificacaoFiscal> findByDescricao(String descricao) {

		return repository.findByDescricao(descricao);

	}

	public Boolean hasDescricao(Integer id, String descricao) {
		return repository.hasDescricao(id, descricao);
	}

	public Boolean hasNcm(Integer id, String ncm) {
		return repository.hasNcm(id, ncm);
	}

	public Page<ClassificacaoFiscal> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<ClassificacaoFiscalView> findByDescricao(String search, Pageable pageable) {

		return repository.findByDescricao(search, pageable);

	}

	public Page<ClassificacaoFiscalView> findByNcm(String search, Pageable pageable) {
		return repository.findByNcm(search, pageable);
	}

	@Transactional(readOnly = false)
	public void update(ClassificacaoFiscalDTO dto) {

		repository.update(dto.getId(), dto.getNcm(), dto.getDescricao());
	}

}
