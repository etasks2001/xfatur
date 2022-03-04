package com.xfatur.repository.cadastro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.TipoValidade;

public interface TipoValidadeRepository extends JpaRepository<TipoValidade, Integer> {
    @Query("select t.id from TipoValidade t where t.descricao=:descricao")
    Integer findByIdDescricao(@Param("descricao") String descricao);

    @Query("select t from TipoValidade t where t.descricao like %:descricao%")
    Page<TipoValidade> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Modifying
    @Query("update TipoValidade tv set tv.descricao = :descricao where tv.id = :id")
    void update(@Param("id") Integer id, @Param("descricao") String descricao);

    @Query("select count(tv)>0 from TipoValidade tv where tv.descricao = :descricao and (:id is null or tv.id <> :id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

}
