package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.exception.RepresentanteNotFoundException;
import com.xfatur.model.Representante;
import com.xfatur.repository.RepresentanteRepository;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository representanteRepository;

    public RepresentanteDTO save(RepresentanteDTO representanteDTO) {
	Representante representante = this.representanteRepository.save(Representante.convert(representanteDTO));
	return DTOConverter.convert(representante);
    }

    public Boolean delete(Integer id) {
	Optional<Representante> found = this.representanteRepository.findById(id);
	if (found.isPresent()) {
	    this.representanteRepository.deleteById(id);
	    return Boolean.TRUE;
	}
	return Boolean.FALSE;
    }

    public RepresentanteDTO findByCNPJCPF(String cnpjcpf) {
	Representante found = this.representanteRepository.findByCNPJCPF(cnpjcpf);
	if (found != null) {
	    return DTOConverter.convert(found);
	}
	throw new RepresentanteNotFoundException("Representante não encontrado");
    }

    public List<RepresentanteDTO> buscaPorNome(String nome) {
	List<Representante> representanteList = this.representanteRepository.buscaPorNome(nome);

	return representanteList.stream().map(DTOConverter::convert).collect(Collectors.toList());

    }
}