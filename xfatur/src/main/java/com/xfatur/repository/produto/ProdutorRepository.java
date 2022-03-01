package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.dto.projections.ProdutorView;
import com.xfatur.model.produto.Produtor;

public interface ProdutorRepository extends JpaRepository<Produtor, Integer> {

	@Query("select p.id from Produtor p where p.descricao=:descricao")
	Integer findByIdDescricao(@Param("descricao") String descricao);

	@Query("select p from Produtor p where p.descricao like %:descricao% order by p.descricao asc")
	Page<Produtor> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

	@Query("select count(p)>0 from Produtor p where p.descricao = :descricao and (:id is null or p.id<>:id)")
	Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

	@Modifying
	@Query("update Produtor p set p.descricao = :descricao where p.id=:id")
	void update(@Param("id") Integer id, @Param("descricao") String descricao);

	@Query("select p.id as id, p.descricao as descricao from Produtor p order by p.descricao asc")
	List<ProdutorView> buscaTodosPorIdDescricao();
}
