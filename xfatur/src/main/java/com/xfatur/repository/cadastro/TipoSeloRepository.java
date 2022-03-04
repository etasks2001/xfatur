package com.xfatur.repository.cadastro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.TipoSelo;

public interface TipoSeloRepository extends JpaRepository<TipoSelo, Integer> {

    @Query("select ts.id from TipoSelo ts where ts.descricao = :descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select ts from TipoSelo ts where ts.descricao like %:descricao% order by ts.descricao asc")
    List<TipoSelo> findByDescricao(@Param("descricao") String descricao);

    @Query("select ts from TipoSelo ts where ts.descricao like %:descricao%")
    Page<TipoSelo> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query("select count(ts)>0 from TipoSelo ts where ts.codigo =:codigo and (:id is null or ts.id<>:id)")
    Boolean hasCodigo(@Param("id") Integer id, @Param("codigo") String codigo);

    @Query("select count(ts)>0 from TipoSelo ts where ts.descricao =:descricao and (:id is null or ts.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

    @Modifying
    @Query("update TipoSelo ts set ts.codigo= :codigo, ts.descricao = :descricao where ts.id = :id")
    void update(@Param("id") Integer id, @Param("codigo") String codigo, @Param("descricao") String descricao);

}
