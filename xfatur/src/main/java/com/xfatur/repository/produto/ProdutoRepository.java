package com.xfatur.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.produto.Produto;

@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select p from Produto p where p.descricao like %:descricao%")
    Page<Produto> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

    Produto findByCodigoProduto(String codigoProduto);

    @Modifying
    @Query(nativeQuery = true, value = "update produto set estoque = estoque + :quantidade where id = :id")
    void entradaEstoque(@Param("id") Integer id, @Param("quantidade") Integer quantidade);

    @Modifying
    @Query(nativeQuery = true, value = "update produto set reservado = reservado + :quantidade where id = :id ")
    void entradaReservado(@Param("id") Integer id, @Param("quantidade") Integer quantidade);

    @Modifying
    @Query(nativeQuery = true, value = "update produto set estoque = estoque - :quantidade where id = :id")
    void saidaEstoque(@Param("id") Integer id, @Param("quantidade") Integer quantidade);

    @Modifying
    @Query(nativeQuery = true, value = "update produto set reservado = reservado - :quantidade where id = :id")
    void saidaReservado(@Param("id") Integer id, @Param("quantidade") Integer quantidade);

    @Query("select p.id from Produto p where p.codigoProduto = :codigoProduto")
    Integer findIdByCodigoProduto(@Param("codigoProduto") String codigoProduto);

    @Modifying
    @Query("update Produto p set p.codigoProduto = :codigoProduto where p.id = :id")
    void update(@Param("id") Integer id, @Param("codigoProduto") String codigoProduto);

}
