package com.xfatur.service.preco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.exception.ListaPrecoIdNotFoundException;
import com.xfatur.model.preco.Lista;
import com.xfatur.repository.preco.ListaRepository;

@Service
@Transactional(readOnly = true)
public class ListaService {

    @Autowired
    private ListaRepository repository;

    @Transactional(readOnly = false)
    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Lista save(Lista listaPreco) {
	return repository.save(listaPreco);
    }

    public Lista findById(Integer id) {
	Optional<Lista> found = repository.findById(id);
	if (found.isPresent()) {
	    return found.get();
	}
	throw new ListaPrecoIdNotFoundException("Lista não encontrada");
    }

}
