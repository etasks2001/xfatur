package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer> {
    @Query("select p.id from Pais p where p.codigoBacen=:codigoBacen")
    Integer findIdByCodigoBacen(@Param("codigoBacen") String codigoBacen);

    @Query("select p from Pais p where p.nome like %:nome% order by p.nome asc")
    List<Pais> findByDescricao(@Param("nome") String nome);

}
