package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

    @Query("select u.id from Unidade u where u.abreviacao =:abreviacao")
    Integer findIdByAbreviacao(@Param("abreviacao") String abreviacao);

    @Query("select u from Unidade u where u.descricao like %:descricao% order by u.descricao asc")
    List<Unidade> findByDescricao(@Param("descricao") String descricao);

}
