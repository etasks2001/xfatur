package com.xfatur.repository.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.ClassificacaoFiscal;

public interface ClassificacaoFiscalRepository extends JpaRepository<ClassificacaoFiscal, Integer> {

    @Query("select cf.id from ClassificacaoFiscal cf where cf.descricao = :descricao")
    Integer findIdByDescricao(@Param("descricao") String descricao);

    @Query("select cf from ClassificacaoFiscal cf where cf.descricao like %:descricao% order by cf.descricao")
    List<ClassificacaoFiscal> findByDescricao(@Param("descricao") String descricao);

    @Query("select count(cf)>0 from ClassificacaoFiscal cf where cf.descricao = :descricao")
    Boolean hasDescricao(@Param("descricao") String descricao);

    @Query("select count(cf)>0 from ClassificacaoFiscal cf where cf.ncm = :ncm")
    Boolean hasNcm(@Param("ncm") String ncm);

    @Query("select cf from ClassificacaoFiscal cf where cf.descricao like %:search% order by cf.descricao")
    Page<ClassificacaoFiscal> findByDescricao(@Param("search") String search, Pageable pageable);

    @Query("select cf from ClassificacaoFiscal cf where cf.ncm like %:search% order by cf.descricao")
    Page<ClassificacaoFiscal> findByNcm(@Param("search") String search, Pageable pageable);

}
