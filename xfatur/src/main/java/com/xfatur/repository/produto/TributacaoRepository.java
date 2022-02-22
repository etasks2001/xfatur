package com.xfatur.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Tributacao;

public interface TributacaoRepository extends JpaRepository<Tributacao, Integer> {

    @Query("select t.id from Tributacao t where t.descricao = :descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select t from Tributacao t where t.descricao like %:descricao%")
    Page<Tributacao> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Modifying
    @Query("update Tributacao t set t.codigo = :codigo, t.descricao = :descricao where t.id=:id")
    void update(@Param("id") Integer id, @Param("codigo") String codigo, @Param("descricao") String descricao);

    @Query("select count(t)>0 from Tributacao t where t.descricao =:descricao and (:id is null or t.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

    @Query("select count(t)>0 from Tributacao t where t.codigo =:codigo and (:id is null or t.id<>:id)")
    Boolean hasCodigo(@Param("id") Integer id, @Param("codigo") String codigo);

}
