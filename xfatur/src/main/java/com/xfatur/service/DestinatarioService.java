package com.xfatur.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xfatur.converter.DTOConverter;
import com.xfatur.converter.ModelConverter;
import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.exception.DestinatarioCNPJCPFExistException;
import com.xfatur.exception.DestinatarioCNPJCPFNotFoundException;
import com.xfatur.exception.DestinatarioIdNotFoundException;
import com.xfatur.model.Destinatario;
import com.xfatur.repository.DestinatarioRepository;

@Service
public class DestinatarioService {
	@Autowired
	private DestinatarioRepository repository;

	public DestinatarioDTO save(DestinatarioDTO d) {
		try {
			Destinatario salvo = repository.save(ModelConverter.convert(d));
			return DTOConverter.convert(salvo);
		} catch (DataIntegrityViolationException e) {
			throw new DestinatarioCNPJCPFExistException("CNPJ/CPF já cadastrado");
		}
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

	public DestinatarioDTO findById(Integer id) {
		Optional<Destinatario> found = repository.findById(id);
		if (found.isPresent()) {
			return DTOConverter.convert(found.get());
		}

		throw new DestinatarioIdNotFoundException("Destinatario não encontrado");
	}

	public List<DestinatarioDTO> buscaPorNome(String nome) {
		List<Destinatario> list = repository.buscaPorNome(nome);

		return list.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}

	public DestinatarioDTO buscaPorCNPJCPF(String cnpjcpf) {
		try {
			Destinatario destinatario = repository.buscaPorCNPJCPF(cnpjcpf);
			return DTOConverter.convert(destinatario);
		} catch (Exception e) {
			throw new DestinatarioCNPJCPFNotFoundException("CNPJ/CPF não encontrado");
		}

	}

	public DestinatarioDTO buscaPorIdComEntrega(Integer id) {
		Destinatario destinatario = repository.buscaPorIdComEntrega(id);
		System.out.println(">>>:" + destinatario);

		return DTOConverter.convert(destinatario);
	}

}