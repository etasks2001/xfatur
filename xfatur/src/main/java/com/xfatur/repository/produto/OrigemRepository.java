package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Origem;

public interface OrigemRepository extends JpaRepository<Origem, Integer> {
    @Query("select o.id from Origem o where o.descricao=:descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select o from Origem o where o.descricao like %:descricao% order by o.descricao asc")
    List<Origem> findByDescricao(@Param("descricao") String descricao);

}
