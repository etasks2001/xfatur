package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select p from Produto p where p.descricao like %:descricao% order by p.descricao asc")
    List<Produto> buscaPorDescricao(@Param("descricao") String descricao);

    Produto findByCodigoProduto(String codigoProduto);

}
