package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.model.Destinatario;
import com.xfatur.repository.DestinatarioRepository;

@Service
public class DestinatarioService {
	@Autowired
	private DestinatarioRepository repository;

	public DestinatarioDTO save(DestinatarioDTO d) {
//	try {
		Destinatario salvo = repository.save(ModelConverter.convert(d));
		return DTOConverter.convert(salvo);
//	} catch (DataIntegrityViolationException e) {
//	    throw new DestinatarioException("CNPJ/CPF já cadastrado");
//	}
	}

	public void deleteById(int id) {
		repository.deleteById(id);

	}
}