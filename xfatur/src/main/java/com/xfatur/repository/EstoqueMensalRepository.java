package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.EstoqueMensal;

public interface EstoqueMensalRepository extends JpaRepository<EstoqueMensal, Integer> {

    @Query("select em from EstoqueMensal em where em.mes = :mes and em.ano = :ano order by em.produto.codigoProduto")
    List<EstoqueMensal> findAllByMesAno(@Param("mes") int mes, @Param("ano") int ano);

}
