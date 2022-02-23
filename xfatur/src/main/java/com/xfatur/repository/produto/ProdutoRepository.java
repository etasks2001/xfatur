package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.produto.Produto;

@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select p from Produto p where p.descricao like %:descricao% order by p.descricao asc")
    List<Produto> buscaPorDescricao(@Param("descricao") String descricao);

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

}
