package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {

    @Query("select t.id from Tipo t where t.descricao = :descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select t from Tipo t where t.descricao like %:descricao% order by t.descricao asc")
    List<Tipo> findByDescricao(@Param("descricao") String descricao);

    @Query("select count(t)>0 from Tipo t where t.descricao=:descricao and (:id is null or t.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

    @Query("select count(t)>0 from Tipo t where t.codigo=:codigo and (:id is null or t.id<>:id)")
    Boolean hasCodigo(@Param("id") Integer id, @Param("codigo") String codigo);

    @Query("select t from Tipo t where t.codigo like %:search%")
    Page<Tipo> findByCodigo(@Param("search") String search, Pageable pageable);

    @Query("select t from Tipo t where t.descricao like %:search%")
    Page<Tipo> findByDescricao(@Param("search") String search, Pageable pageable);

    @Modifying
    @Query("update Tipo t set t.codigo = :codigo, t.descricao = :descricao where id=:id")
    void update(@Param("id") Integer id, @Param("codigo") String codigo, @Param("descricao") String descricao);

}
