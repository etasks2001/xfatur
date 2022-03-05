package com.xfatur.repository.cadastro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.ClassificacaoFiscal;
import com.xfatur.repository.projections.cadastro.ClassificacaoFiscalView;

public interface ClassificacaoFiscalRepository extends JpaRepository<ClassificacaoFiscal, Integer> {

	@Query("select cf.id from ClassificacaoFiscal cf where cf.descricao = :descricao")
	Integer findIdByDescricao(@Param("descricao") String descricao);

	@Query("select cf from ClassificacaoFiscal cf where cf.descricao like %:descricao% order by cf.descricao")
	List<ClassificacaoFiscal> findByDescricao(@Param("descricao") String descricao);

	@Query("select cf.id as id, cf.ncm as ncm, cf.descricao as descricao from ClassificacaoFiscal cf where cf.descricao like %:search%")
	Page<ClassificacaoFiscalView> findByDescricao(@Param("search") String search, Pageable pageable);

	@Query("select cf.id as id, cf.ncm as ncm, cf.descricao as descricao  from ClassificacaoFiscal cf where cf.ncm like %:search%")
	Page<ClassificacaoFiscalView> findByNcm(@Param("search") String search, Pageable pageable);

	@Query("select count(cf)>0 from ClassificacaoFiscal cf where cf.descricao = :descricao and (:id is null or cf.id <>:id)")
	Boolean hasDescricao(Integer id, String descricao);

	@Query(value = "select exists (select * from ClassificacaoFiscal cf where cf.ncm = :ncm and(:id is null or cf.id <> :id))", nativeQuery = true)
	Boolean hasNcm(@Param("id") Integer id, @Param("ncm") String ncm);

	@Modifying
	@Query("update ClassificacaoFiscal cf set cf.ncm = :ncm, cf.descricao = :descricao  where cf.id = :id")
	void update(@Param("id") Integer id, @Param("ncm") String ncm, @Param("descricao") String lastname);

}
