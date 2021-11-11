package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xfatur.model.Emitente;

@Repository
public interface EmitenteRepository extends JpaRepository<Emitente, Integer> {

    Emitente findByCNPJ(String cnpj);

    @Query("select e from Emitente e where e.xNome like %:nome%")
    List<Emitente> buscaPorNome(@Param("nome") String name);

}
