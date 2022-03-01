package com.xfatur.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.projections.ProdutoView;
import com.xfatur.model.produto.Produto;

@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("select p.id as id, p.codigoProduto as codigoProduto, p.descricao as descricao, p.unidadeDetalhada as unidadedetalhada, pa.nome as pais, pr.descricao as produtor, p.cest as cest, p.ipiUnitario as ipiunitario,p.aliquotaipi as aliquotaipi  from Produto p join p.pais pa join p.produtor pr where p.descricao like %:descricao%")
	Page<ProdutoView> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

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

	@Query("select count(p)>0 from Produto p where p.codigoProduto = :codigoProduto and (:id is null or p.codigoProduto <>:id)")
	Boolean hasCodigoProduto(@Param("id") Integer id, @Param("codigoProduto") String codigoProduto);

}
