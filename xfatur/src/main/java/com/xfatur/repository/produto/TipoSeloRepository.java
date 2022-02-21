package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.TipoSelo;

public interface TipoSeloRepository extends JpaRepository<TipoSelo, Integer> {

    @Query("select ts.id from TipoSelo ts where ts.descricao = :descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select ts from TipoSelo ts where ts.descricao like %:descricao% order by ts.descricao asc")
    List<TipoSelo> findByDescricao(@Param("descricao") String descricao);

}
