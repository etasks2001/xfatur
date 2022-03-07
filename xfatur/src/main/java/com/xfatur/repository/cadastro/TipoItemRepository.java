package com.xfatur.repository.cadastro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.TipoItem;
import com.xfatur.repository.projections.cadastro.TipoItemView;

public interface TipoItemRepository extends JpaRepository<TipoItem, Integer> {

    @Query("select ti.id from TipoItem ti where ti.descricao = :descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select ti from TipoItem ti where ti.descricao like %:descricao% order by ti.descricao asc")
    List<TipoItem> findByDescricao(@Param("descricao") String descricao);

    @Query("select count(ti)>0 from TipoItem ti where ti.descricao=:descricao and (:id is null or ti.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

    @Query("select count(ti)>0 from TipoItem ti where ti.codigo=:codigo and (:id is null or ti.id<>:id)")
    Boolean hasCodigo(@Param("id") Integer id, @Param("codigo") String codigo);

    @Query("select ti.id as id, ti.codigo as codigo, ti.descricao as descricao from TipoItem ti where ti.codigo like %:search%")
    Page<TipoItemView> findByCodigo(@Param("search") String search, Pageable pageable);

    @Query("select ti.id as id, ti.codigo as codigo, ti.descricao as descricao from TipoItem ti where ti.descricao like %:search%")
    Page<TipoItemView> findByDescricao(@Param("search") String search, Pageable pageable);

    @Modifying
    @Query("update TipoItem ti set ti.codigo = :codigo, ti.descricao = :descricao where id=:id")
    void update(@Param("id") Integer id, @Param("codigo") String codigo, @Param("descricao") String descricao);

}
