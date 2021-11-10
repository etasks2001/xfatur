package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.exception.EmitenteNotFoundException;
import com.xfatur.model.Emitente;
import com.xfatur.repository.EmitenteRepository;

@Service
public class EmitenteService {

    @Autowired
    private EmitenteRepository emitRepository;

    public List<EmitenteDTO> getAll() {
	List<Emitente> emitente = this.emitRepository.findAll();

	return emitente.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public EmitenteDTO findById(Integer id) {
	Optional<Emitente> emitente = this.emitRepository.findById(id);
	if (emitente.isPresent()) {
	    return DTOConverter.convert(emitente.get());
	}
	throw new EmitenteNotFoundException("Não encontrado");
    }

    public EmitenteDTO save(EmitenteDTO emitenteDTO) {
	Emitente emit = this.emitRepository.save(Emitente.convert(emitenteDTO));

	return DTOConverter.convert(emit);
    }

    public Boolean delete(Integer id) {
	Optional<Emitente> emitente = this.emitRepository.findById(id);

	if (emitente.isPresent()) {
	    this.emitRepository.delete(emitente.get());
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;

    }

    public EmitenteDTO findByCNPJ(String cnpj) {
	Emitente emitente = this.emitRepository.findByCNPJ(cnpj);

	if (emitente != null) {
	    return DTOConverter.convert(emitente);

	}
	throw new EmitenteNotFoundException("Não encontrado");
    }

    public EmitenteDTO queryByxName(String nome) {
	Emitente emitente = this.emitRepository.queryByxNome(nome);
	if (emitente != null) {
	    return DTOConverter.convert(emitente);
	}

	throw new EmitenteNotFoundException("Não encontrado");

    }

}
