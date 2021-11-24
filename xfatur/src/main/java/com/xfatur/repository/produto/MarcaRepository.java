package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    @Query("select m.id from Marca m where m.descricao=:descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select m from Marca m where m.descricao like %:descricao% order by m.descricao asc")
    List<Marca> findByDescricao(@Param("descricao") String descricao);

}
