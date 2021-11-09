package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.EmitDTO;
import com.xfatur.exception.EmitNotFoundException;
import com.xfatur.model.Emit;
import com.xfatur.repository.EmitRepository;

@Service
public class EmitService {

    @Autowired
    private EmitRepository emitRepository;

    public List<EmitDTO> getAll() {
	List<Emit> emit = this.emitRepository.findAll();

	return emit.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public EmitDTO findById(Integer id) {
	Optional<Emit> emit = this.emitRepository.findById(id);
	if (emit.isPresent()) {
	    return DTOConverter.convert(emit.get());
	}
	throw new EmitNotFoundException("Não encontrado");
    }

    public EmitDTO save(EmitDTO emitDTO) {
	Emit emit = this.emitRepository.save(Emit.convert(emitDTO));

	return DTOConverter.convert(emit);
    }

    public Boolean delete(Integer id) {
	Optional<Emit> emit = this.emitRepository.findById(id);

	if (emit.isPresent()) {
	    this.emitRepository.delete(emit.get());
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;

    }

    public EmitDTO findByCNPJ(String cnpj) {
	Emit emit = this.emitRepository.findByCNPJ(cnpj);

	if (emit != null) {
	    return DTOConverter.convert(emit);

	}
	throw new EmitNotFoundException("Não encontrado");
    }

    public EmitDTO queryByxName(String nome) {
	Emit emit = this.emitRepository.queryByxNome(nome);
	if (emit != null) {
	    return DTOConverter.convert(emit);
	}

	throw new EmitNotFoundException("Não encontrado");

    }

}
