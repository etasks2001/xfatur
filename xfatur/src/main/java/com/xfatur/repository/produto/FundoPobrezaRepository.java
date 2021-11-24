package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.FundoPobreza;

public interface FundoPobrezaRepository extends JpaRepository<FundoPobreza, Integer> {
    @Query("select fp.id from FundoPobreza fp where fp.descricao=:descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select fp from FundoPobreza fp where fp.descricao like %:descricao% order by fp.descricao asc")
    List<FundoPobreza> findByDescricao(@Param("descricao") String descricao);

}
