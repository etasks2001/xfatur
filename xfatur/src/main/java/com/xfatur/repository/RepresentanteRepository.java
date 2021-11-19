package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.Representante;

public interface RepresentanteRepository extends JpaRepository<Representante, Integer> {

    Representante findByCNPJCPF(String cnpj);

    @Query("select r from Representante r where r.xNome like %:nome% order by r.xNome asc")
    List<Representante> buscaPorNome(@Param("nome") String nome);

    @Query("select r.id from Representante r where cnpjcpf=:cnpjcpf")
    Integer findIdByCNPJCPF(@Param("cnpjcpf") String cnpjcpf);

}
