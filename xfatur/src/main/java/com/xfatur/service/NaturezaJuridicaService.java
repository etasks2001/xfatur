package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.exception.NaturezaJuridicaNotFoundException;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.repository.NaturezaJuridicaRepository;

@Service
public class NaturezaJuridicaService {

    @Autowired
    private NaturezaJuridicaRepository naturezaJuridicaRepository;

    public NaturezaJuridicaDTO save(NaturezaJuridicaDTO naturezaJuridicaDTO) {
	NaturezaJuridica naturezaJuridica = this.naturezaJuridicaRepository.save(NaturezaJuridica.convert(naturezaJuridicaDTO));

	return DTOConverter.convert(naturezaJuridica);
    }

    public Boolean delete(Integer id) {

	Optional<NaturezaJuridica> naturezaJuridica = this.naturezaJuridicaRepository.findById(id);
	if (naturezaJuridica.isPresent()) {
	    this.naturezaJuridicaRepository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;

    }

    public List<NaturezaJuridicaDTO> queryByDescricao(String descricao) {

	List<NaturezaJuridica> queryByDescricao = this.naturezaJuridicaRepository.queryByDescricao(descricao);

	return queryByDescricao.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public NaturezaJuridicaDTO findById(int id) {
	Optional<NaturezaJuridica> findById = this.naturezaJuridicaRepository.findById(id);
	if (findById.isPresent()) {
	    return DTOConverter.convert(findById.get());
	}
	throw new NaturezaJuridicaNotFoundException("Natureza Jurídica não encontrada");

    }

}
