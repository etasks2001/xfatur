package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.RegiaoProdutora;

public interface RegiaoProdutoraRepository extends JpaRepository<RegiaoProdutora, Integer> {
    @Query("select rp.id from RegiaoProdutora rp where rp.descricao=:descricao")
    Integer findByIdDescricao(@Param("descricao") String descricao);

    @Query("select rp from RegiaoProdutora rp where rp.descricao like %:descricao% order by rp.descricao asc")
    List<RegiaoProdutora> findByDescricao(@Param("descricao") String descricao);

}
