package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.exception.RepresentanteException;
import com.xfatur.exception.RepresentanteNotFoundException;
import com.xfatur.model.Representante;
import com.xfatur.repository.RepresentanteRepository;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository repository;

    public RepresentanteDTO save(RepresentanteDTO representanteDTO) {
	try {
	    Representante representante = repository.save(ModelConverter.convert(representanteDTO));
	    return DTOConverter.convert(representante);
	} catch (DataIntegrityViolationException e) {
	    throw new RepresentanteException("CNPJ/CPF já cadastrado");
	}
    }

    public Boolean delete(Integer id) {
	Optional<Representante> found = repository.findById(id);
	if (found.isPresent()) {
	    repository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;
    }

    public RepresentanteDTO findByCNPJCPF(String cnpjcpf) {
	Representante found = repository.findByCNPJCPF(cnpjcpf);
	if (found != null) {
	    return DTOConverter.convert(found);
	}
	throw new RepresentanteNotFoundException("Representante não encontrado");
    }

    public List<RepresentanteDTO> buscaPorNome(String nome) {
	List<Representante> representanteList = repository.buscaPorNome(nome);

	return representanteList.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

}