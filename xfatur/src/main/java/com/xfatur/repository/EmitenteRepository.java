package com.xfatur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xfatur.model.Emitente;

@Repository
public interface EmitenteRepository extends JpaRepository<Emitente, Integer> {

    Emitente findByCNPJ(String cnpj);

    Emitente queryByxNome(String name);

}
