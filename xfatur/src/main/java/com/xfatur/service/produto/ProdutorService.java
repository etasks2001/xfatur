package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ProdutorIdNotFoundException;
import com.xfatur.model.produto.Produtor;
import com.xfatur.repository.cadastro.ProdutorRepository;
import com.xfatur.repository.mappers.ModelMapper;
import com.xfatur.repository.projections.cadastro.ProdutorView;
import com.xfatur.validation.dto.cadastro.ProdutorDTO;

@Service
@Transactional(readOnly = true)
public class ProdutorService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProdutorRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findByIdDescricao(String descricao) {
	return repository.findByIdDescricao(descricao);
    }

    @Transactional(readOnly = false)
    public ProdutorDTO save(ProdutorDTO produtor) {
	Produtor saved = repository.save(mapper.toModel(produtor));
	return mapper.toDto(saved);
    }

    public ProdutorDTO findById(int id) {
	Optional<Produtor> produtor = repository.findById(id);
	if (produtor.isPresent()) {
	    return mapper.toDto(produtor.get());
	}

	throw new ProdutorIdNotFoundException("Código do Produtor não encontrado");
    }

    public Page<ProdutorView> findByDescricao(String descricao, Pageable pageable) {
	return repository.findByDescricao(descricao, pageable);
    }

    public Boolean hasDescricao(Integer id, String descricao) {
	return repository.hasDescricao(id, descricao);
    }

    @Transactional
    public void update(ProdutorDTO dto) {
	repository.update(dto.getId(), dto.getDescricao());
    }

    public List<ProdutorView> buscaTodosPorIdDescricao() {
	return repository.buscaTodosPorIdDescricao();
    }

    public List<ProdutorDTO> findAll() {
	List<Produtor> all = repository.findAll();
	System.out.println(all.size());

	List<ProdutorDTO> collect = all.stream().map(model -> mapper.toDto(model)).collect(Collectors.toList());
	System.out.println(collect.get(0));

	return collect;
    }

}