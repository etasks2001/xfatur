package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.exception.RamoAtividadeNotFoundException;
import com.xfatur.model.RamoAtividade;
import com.xfatur.repository.RamoAtividadeRepository;

@Service
public class RamoAtividadeService {

    @Autowired
    private RamoAtividadeRepository ramoAtividadeRepository;

    public RamoAtividadeDTO save(RamoAtividadeDTO ramoAtividadeDTO) {
	RamoAtividade ramoAtividade = this.ramoAtividadeRepository.save(RamoAtividade.convert(ramoAtividadeDTO));

	return DTOConverter.convert(ramoAtividade);
    }

    public Boolean delete(Integer id) {

	Optional<RamoAtividade> ramoAtividade = this.ramoAtividadeRepository.findById(id);
	if (ramoAtividade.isPresent()) {
	    this.ramoAtividadeRepository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;

    }

    public List<RamoAtividadeDTO> queryByDescricao(String descricao) {

	List<RamoAtividade> queryByDescricao = this.ramoAtividadeRepository.queryByDescricao(descricao);

	return queryByDescricao.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public RamoAtividadeDTO findById(int id) {
	Optional<RamoAtividade> findById = this.ramoAtividadeRepository.findById(id);
	if (findById.isPresent()) {
	    return DTOConverter.convert(findById.get());
	}
	throw new RamoAtividadeNotFoundException("Ramo de Atividade não encontrado.");

    }

}
