package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.PaisIdNotFoundException;
import com.xfatur.model.produto.Pais;
import com.xfatur.repository.produto.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByCodigoBacen(String codigoBacen) {
	return repository.findIdByCodigoBacen(codigoBacen);
    }

    public Pais save(Pais pais) {
	return repository.save(pais);
    }

    public Pais findById(int id) {
	Optional<Pais> pais = repository.findById(id);
	if (pais.isPresent()) {
	    return pais.get();
	}

	throw new PaisIdNotFoundException("Código do Pais não encontrado");
    }

    public List<Pais> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
