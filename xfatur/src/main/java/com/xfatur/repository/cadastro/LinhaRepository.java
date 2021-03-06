package com.xfatur.repository.cadastro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Linha;
import com.xfatur.repository.projections.cadastro.LinhaView;

public interface LinhaRepository extends JpaRepository<Linha, Integer> {
    @Query("select l.id from Linha l where l.descricao=:descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select l.id as id, l.descricao as descricao, l.tipo as tipo from Linha l where l.descricao like %:descricao%")
    Page<LinhaView> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Modifying
    @Query("update Linha l set l.descricao=:descricao, l.tipo=:tipo where l.id=:id")
    void update(@Param("id") Integer id, @Param("descricao") String descricao, @Param("tipo") String tipo);

    @Query("select count(l)>0 from Linha l where l.descricao = :descricao and (:id is null or l.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

}
