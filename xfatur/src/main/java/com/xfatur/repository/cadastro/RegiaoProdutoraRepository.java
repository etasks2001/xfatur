package com.xfatur.repository.cadastro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.RegiaoProdutora;
import com.xfatur.repository.projections.cadastro.RegiaoProdutoraView;

public interface RegiaoProdutoraRepository extends JpaRepository<RegiaoProdutora, Integer> {
    @Query("select rp.id from RegiaoProdutora rp where rp.descricao=:descricao")
    Integer findByIdDescricao(@Param("descricao") String descricao);

    @Query("select rp from RegiaoProdutora rp where rp.descricao like %:descricao% order by rp.descricao asc")
    Page<RegiaoProdutoraView> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    @Modifying
    @Query("update RegiaoProdutora rp set rp.descricao =:descricao where rp.id=:id")
    void update(@Param("id") Integer id, @Param("descricao") String descricao);

    @Query("select count(rp)>0 from RegiaoProdutora rp where rp.descricao = :descricao and (:id is null or rp.id<>:id)")
    Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

}
