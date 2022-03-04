package com.xfatur.repository.cadastro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

    @Query("select u.id from Unidade u where u.abreviacao =:abreviacao")
    Integer findIdByAbreviacao(@Param("abreviacao") String abreviacao);

    @Query("select u from Unidade u where u.descricao like %:descricao% ")
    Page<Unidade> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Query("select u from Unidade u where u.abreviacao like %:search% ")
    Page<Unidade> findByAbreviacao(@Param("search") String search, Pageable pageable);

    @Modifying
    @Query("update Unidade u set u.descricao = :descricao, u.abreviacao = :abreviacao where u.id= :id")
    void update(@Param("id") Integer id, @Param("descricao") String descricao, @Param("abreviacao") String abreviacao);

    @Query("select count(u)>0 from Unidade u where u.abreviacao = :abreviacao and (:id is null or u.id<>:id)")
    Boolean hasAbreviacao(@Param("id") Integer id, @Param("abreviacao") String abreviacao);

    @Query("select count(u)>0 from Unidade u where u.descricao = :descricao and (:id is null or u.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

}
