package com.xfatur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xfatur.model.Emit;

@Repository
public interface EmitRepository extends JpaRepository<Emit, Integer> {

    Emit findByCNPJ(String cnpj);

    Emit queryByxNome(String name);

}
