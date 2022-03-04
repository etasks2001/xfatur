package com.xfatur.repository.cadastro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	@Query("select m.id from Marca m where m.descricao=:descricao")
	Integer findIdByDescricao(@Param("descricao") String descricao);

	@Query("select m from Marca m where m.descricao like %:descricao% order by m.descricao asc")
	List<Marca> findByDescricao(@Param("descricao") String descricao);

	@Modifying
	@Query("update Marca m set m.descricao =:descricao where m.id=:id ")
	void update(@Param("id") Integer id, @Param("descricao") String descricao);

	@Query("select m from Marca m where m.descricao like %:search%")
	Page<Marca> findByDescricao(String search, Pageable pageable);

	@Query("select count(m)>0 from Marca m where m.descricao = :descricao and (:id is null or m.id <>:id)")
	Boolean hasDescricao(@Param("id") Integer id, @Param("descricao") String descricao);

}
