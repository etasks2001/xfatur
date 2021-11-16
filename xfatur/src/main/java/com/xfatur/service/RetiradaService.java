package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.RetiradaDTO;
import com.xfatur.model.Retirada;
import com.xfatur.repository.RetiradaRepository;

@Service
public class RetiradaService {

    @Autowired
    private RetiradaRepository repository;

    public RetiradaDTO save(RetiradaDTO retiradaDTO) {

	Retirada saved = repository.save(ModelConverter.convert(retiradaDTO));

	return DTOConverter.convert(saved);
    }

}
