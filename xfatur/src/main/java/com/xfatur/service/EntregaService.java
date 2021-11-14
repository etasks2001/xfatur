package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.EntregaDTO;
import com.xfatur.model.Entrega;
import com.xfatur.repository.EntregaRepository;

@Service
public class EntregaService {
	@Autowired
	private EntregaRepository repository;

	public EntregaDTO save(EntregaDTO entregaDTO) {
		Entrega saved = repository.save(ModelConverter.convert(entregaDTO));
		return DTOConverter.convert(saved);
	}

	public void deleteById(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}

	}

}
