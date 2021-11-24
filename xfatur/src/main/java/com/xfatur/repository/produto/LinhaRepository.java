package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Linha;

public interface LinhaRepository extends JpaRepository<Linha, Integer> {
    @Query("select l.id from Linha l where l.descricao=:descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select l from Linha l where l.descricao like %:descricao% order by l.descricao asc")
    List<Linha> findByDescricao(@Param("descricao") String descricao);

}
