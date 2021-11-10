package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.NaturezaJuridica;

public interface NaturezaJuridicaRepository extends JpaRepository<NaturezaJuridica, Integer> {

    @Query("select nj from NaturezaJuridica nj where nj.descricao like %:descricao%")
    List<NaturezaJuridica> queryByDescricao(@Param("descricao") String descricao);
}
