package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.RamoAtividade;

public interface RamoAtividadeRepository extends JpaRepository<RamoAtividade, Integer> {

    @Query("select ra from RamoAtividade ra where ra.descricao like %:descricao% order by ra.descricao asc")
    List<RamoAtividade> buscaPorDescricao(@Param("descricao") String descricao);

}
