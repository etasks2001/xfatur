package com.xfatur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.DestinatarioCNPJCPFExistException;
import com.xfatur.exception.DestinatarioIdNotFoundException;
import com.xfatur.model.Destinatario;
import com.xfatur.repository.DestinatarioRepository;

@Service
@Transactional(readOnly = true)
public class DestinatarioService {
    @Autowired
    private DestinatarioRepository repository;

    @Transactional(readOnly = false)
    public Destinatario save(Destinatario destinatario) {

	try {
	    Destinatario saved = repository.save(destinatario);
	    return saved;
	} catch (Exception e) {
	    throw new DestinatarioCNPJCPFExistException("CNPJ/CPF já cadastrado");
	}
    }

    public void deleteById(int id) {
	repository.deleteById(id);
    }

    public Destinatario findById(Integer id) {
	Optional<Destinatario> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();
	}

	throw new DestinatarioIdNotFoundException("Destinatario não encontrado");
    }

    public List<Destinatario> buscaPorNome(String nome) {
	List<Destinatario> list = repository.buscaPorNome(nome);

	return list;
    }

    public Destinatario buscaPorCNPJCPF(String cnpjcpf) {
	Destinatario destinatario = repository.buscaPorCNPJCPF(cnpjcpf);
	return destinatario;

    }

}