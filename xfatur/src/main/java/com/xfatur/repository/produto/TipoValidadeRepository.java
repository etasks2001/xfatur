package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.TipoValidade;

public interface TipoValidadeRepository extends JpaRepository<TipoValidade, Integer> {
    @Query("select t.id from TipoValidade t where t.descricao=:descricao")
    Integer findByIdDescricao(@Param("descricao") String descricao);

    @Query("select t from TipoValidade t where t.descricao like %:descricao% order by t.descricao asc")
    List<TipoValidade> findByDescricao(@Param("descricao") String descricao);

}
