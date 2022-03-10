package com.xfatur.service.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.MarcaIdNotFoundException;
import com.xfatur.model.produto.Marca;
import com.xfatur.repository.cadastro.MarcaRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.MarcaView;
import com.xfatur.validation.dto.cadastro.MarcaDTO;

@Service
@Transactional(readOnly = true)
public class MarcaService {

    @Autowired
    private MarcaRepository repository;
    @Autowired
    private ModelMapper mapper;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    @Transactional(readOnly = false)
    public MarcaDTO save(MarcaDTO marca) {
	Marca saved = repository.save(mapper.toModel(marca));
	return mapper.toDto(saved);
    }

    public MarcaDTO findById(int id) {
	Optional<Marca> marca = repository.findById(id);
	if (marca.isPresent()) {
	    return mapper.toDto(marca.get());
	}

	throw new MarcaIdNotFoundException("Código da Marca não encontrado");
    }

    @Transactional
    public void update(MarcaDTO dto) {
	repository.update(dto.getId(), dto.getDescricao());

    }

    public Page<MarcaView> findByDescricao(String search, Pageable pageable) {
	return repository.findByDescricao(search, pageable);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

}
