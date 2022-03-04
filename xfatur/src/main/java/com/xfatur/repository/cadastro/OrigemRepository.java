package com.xfatur.repository.cadastro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Origem;

public interface OrigemRepository extends JpaRepository<Origem, Integer> {
	@Query("select o.id from Origem o where o.descricao=:descricao")
	Integer findIdByDescricao(@Param("descricao") String descricao);

	@Query("select o from Origem o where o.descricao like %:descricao%")
	Page<Origem> findByDescricao(@Param("descricao") String descricao, Pageable pageable);

	@Query("select count(o)>0 from Origem o where o.descricao = :descricao and (:id is null or o.id <>:id)")
	Boolean hasDescricao(Integer id, String descricao);

	@Query("select count(o)>0 from Origem o where o.codigo = :codigo and (:id is null or o.id <> :id)")
	Boolean hasCodigo(Integer id, String codigo);

	@Modifying
	@Query("update Origem o set o.codigo=:codigo, o.descricao = :descricao where o.id=:id ")
	void update(@Param("id") Integer id, @Param("codigo") String codigo, @Param("descricao") String descricao);

}
