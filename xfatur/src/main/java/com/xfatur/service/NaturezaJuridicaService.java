package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.exception.NaturezaJuridicaException;
import com.xfatur.exception.NaturezaJuridicaIdNotFoundException;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.repository.NaturezaJuridicaRepository;

@Service
public class NaturezaJuridicaService {

	@Autowired
	private NaturezaJuridicaRepository repository;

	public NaturezaJuridicaDTO save(NaturezaJuridicaDTO naturezaJuridicaDTO) {
		try {
			NaturezaJuridica naturezaJuridica = repository.save(ModelConverter.convert(naturezaJuridicaDTO));

			return DTOConverter.convert(naturezaJuridica);
		} catch (DataIntegrityViolationException e) {
			throw new NaturezaJuridicaException("Descrição já cadastrada");
		}
	}

	public Boolean delete(Integer id) {
		Optional<NaturezaJuridica> naturezaJuridica = repository.findById(id);
		if (naturezaJuridica.isPresent()) {
			repository.deleteById(id);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public List<NaturezaJuridicaDTO> buscaPorDescricao(String descricao) {
		List<NaturezaJuridica> queryByDescricao = repository.buscaPorDescricao(descricao);

		return queryByDescricao.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}

	public NaturezaJuridicaDTO findById(int id) {
		Optional<NaturezaJuridica> findById = repository.findById(id);
		if (findById.isPresent()) {
			return DTOConverter.convert(findById.get());
		}
		throw new NaturezaJuridicaIdNotFoundException("Natureza Jurídica não encontrada");
	}
}