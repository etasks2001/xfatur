package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Produtor;

public interface ProdutorRepository extends JpaRepository<Produtor, Integer> {

    @Query("select p.id from Produtor p where p.descricao=:descricao")
    Integer findByIdDescricao(@Param("descricao") String descricao);

    @Query("select p from Produtor p where p.descricao like %:descricao%")
    List<Produtor> findByDescricao(@Param("descricao") String descricao);

}
