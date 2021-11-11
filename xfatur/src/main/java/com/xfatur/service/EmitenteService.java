package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.exception.EmitenteException;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.model.Emitente;
import com.xfatur.repository.EmitenteRepository;

@Service
public class EmitenteService {

    @Autowired
    private EmitenteRepository repository;

    public EmitenteDTO findById(Integer id) {
	Optional<Emitente> emitente = repository.findById(id);
	if (emitente.isPresent()) {
	    return DTOConverter.convert(emitente.get());
	}
	throw new EmitenteNotFoundException("Emitente Não encontrado");
    }

    public EmitenteDTO save(EmitenteDTO emitenteDTO) {
	try {
	    Emitente emit = repository.save(ModelConverter.convert(emitenteDTO));

	    return DTOConverter.convert(emit);
	} catch (DataIntegrityViolationException e) {
	    throw new EmitenteException("CNPJ/CPF já cadastrado");
	}
    }

    public Boolean delete(Integer id) {
	Optional<Emitente> emitente = repository.findById(id);

	if (emitente.isPresent()) {
	    repository.delete(emitente.get());
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;

    }

    public EmitenteDTO findByCNPJ(String cnpj) {
	Emitente emitente = repository.findByCNPJ(cnpj);

	if (emitente != null) {
	    return DTOConverter.convert(emitente);
	}
	throw new EmitenteNotFoundException("Emitente Não encontrado");
    }

    public List<EmitenteDTO> buscaPorNome(String nome) {
	List<Emitente> emitentes = repository.buscaPorNome(nome);

	return emitentes.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }
}