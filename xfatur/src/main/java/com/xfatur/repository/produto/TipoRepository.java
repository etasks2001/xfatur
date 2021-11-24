package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, String> {

    @Query("select t.id from Tipo t where t.descricao = :descricao")
    String findIdByDescricao(@Param("descricao") String descricao);

    @Query("select t from Tipo t where t.descricao like %:descricao% order by t.descricao asc")
    List<Tipo> findByDescricao(@Param("descricao") String descricao);

}
