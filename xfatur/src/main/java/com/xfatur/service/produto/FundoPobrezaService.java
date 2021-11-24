package com.xfatur.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.exception.FundoPobrezaIdNotFoundException;
import com.xfatur.model.produto.FundoPobreza;
import com.xfatur.repository.produto.FundoPobrezaRepository;

@Service
public class FundoPobrezaService {

    @Autowired
    private FundoPobrezaRepository repository;

    public void deleteById(Integer id) {
	repository.deleteById(id);
    }

    public Integer findIdByDescricao(String descricao) {
	return repository.findIdByDescricao(descricao);
    }

    public FundoPobreza save(FundoPobreza fundoPobreza) {
	return repository.save(fundoPobreza);
    }

    public FundoPobreza findById(int id) {
	Optional<FundoPobreza> fundoPobreza = repository.findById(id);
	if (fundoPobreza.isPresent()) {
	    return fundoPobreza.get();
	}

	throw new FundoPobrezaIdNotFoundException("Código do Fundo Pobreza não encontrado");
    }

    public List<FundoPobreza> findByDescricao(String descricao) {
	return repository.findByDescricao(descricao);
    }

}
