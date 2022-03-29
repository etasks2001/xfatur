package com.xfatur.service.produto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ClassificacaoFiscalCodigoNaoEncontrado;
import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.repository.cadastro.ClassificacaoFiscalRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.ClassificacaoFiscalView;
import com.xfatur.service.Servico;
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;

@Service(value = "classificacaofiscalservice")
@Transactional(readOnly = true)
public class ClassificacaoFiscalService implements Servico {

    private static Logger LOG = LoggerFactory.getLogger(ClassificacaoFiscalService.class);

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClassificacaoFiscalRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public ClassificacaoFiscalDTO save(ClassificacaoFiscalDTO classificacaoFiscalDTO) {

	ClassificacaoFiscal saved = repository.save(mapper.toModel(classificacaoFiscalDTO));

	LOG.info("ClassificacaoFiscal incluída com id " + saved.getId() + "");
	return mapper.toDto(saved);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public ClassificacaoFiscalDTO findById(Integer id) {
	return mapper.toDto(repository.findById(id).orElseThrow(() -> new ClassificacaoFiscalCodigoNaoEncontrado("Código da classificação fiscal não encontrado.")));
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    public Boolean hasNcm(Integer id, String ncm) {

	return repository.hasNcm(id, ncm);
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

	LOG.info("ClassificacaoFiscal alterada com id " + dto.getId() + "");
    }

}
