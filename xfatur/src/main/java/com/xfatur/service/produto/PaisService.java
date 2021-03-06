package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.PaisIdNotFoundException;
import com.xfatur.model.produto.Pais;
import com.xfatur.repository.cadastro.PaisRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.PaisView;
import com.xfatur.validation.dto.cadastro.PaisDTO;

@Service
@Transactional(readOnly = true)
public class PaisService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaisRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByCodigoBacen(String codigoBacen) {
	return repository.findIdByCodigoBacen(codigoBacen);
    }

    @Transactional(readOnly = false)
    public PaisDTO save(PaisDTO pais) {
	Pais saved = repository.save(mapper.toModel(pais));
	return mapper.toDto(saved);
    }

    public PaisDTO findById(int id) {
	Optional<Pais> pais = repository.findById(id);
	if (pais.isPresent()) {
	    return mapper.toDto(pais.get());
	}

	throw new PaisIdNotFoundException("Código do Pais não encontrado");
    }

    @Transactional(readOnly = false)
    public void update(PaisDTO dto) {
	repository.update(dto.getId(), dto.getNome(), dto.getSigla(), dto.getOrigem(), dto.getCodigoBacen());
    }

    public Boolean hasNome(Integer id, String nome) {
	return repository.hasNome(id, nome);
    }

    public Boolean hasSigla(Integer id, String sigla) {
	return repository.hasSigla(id, sigla);
    }

    public Boolean hasOrigem(Integer id, String origem) {
	return repository.hasOrigem(id, origem);
    }

    public Boolean hasCodigoBacen(Integer id, String codigoBacen) {
	return repository.hasCodigoBacen(id, codigoBacen);
    }

    public Page<PaisView> findByNome(String search, Pageable pageable) {
	return repository.findByNome(search, pageable);
    }

    public List<PaisView> buscaTodosPorIdNome() {
	return repository.buscaTodosPorIdNome();
    }

}
