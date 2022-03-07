package com.xfatur.repository.cadastro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.produto.Pais;
import com.xfatur.repository.projections.cadastro.PaisView;

public interface PaisRepository extends JpaRepository<Pais, Integer> {
    @Query("select p.id from Pais p where p.codigoBacen=:codigoBacen")
    Integer findIdByCodigoBacen(@Param("codigoBacen") String codigoBacen);

    @Query("select p.id as id, p.nome as nome, p.sigla as sigla, p.origem as origem, p.codigoBacen as codigoBacen from Pais p where p.nome like %:search%")
    Page<PaisView> findByNome(@Param("search") String search, Pageable pageable);

    @Modifying
    @Query("update Pais p set p.nome=:nome, p.sigla=:sigla, p.origem=:origem, p.codigoBacen=:codigoBacen where p.id = :id")
    void update(@Param("id") Integer id, @Param("nome") String nome, @Param("sigla") String sigla, @Param("origem") String origem, @Param("codigoBacen") String codigoBacen);

    @Query("select count(p)>0 from Pais p where p.nome = :nome and (:id is null or p.id <> :id)")
    Boolean hasNome(@Param("id") Integer id, @Param("nome") String nome);

    @Query("select count(p)>0 from Pais p where p.sigla = :sigla and (:id is null or p.id <> :id)")
    Boolean hasSigla(@Param("id") Integer id, @Param("sigla") String sigla);

    @Query("select count(p)>0 from Pais p where p.origem = :origem and (:id is null or p.id <> :id)")
    Boolean hasOrigem(@Param("id") Integer id, @Param("origem") String origem);

    @Query("select count(p)>0 from Pais p where p.codigoBacen = :codigoBacen and (:id is null or p.id <> :id)")
    Boolean hasCodigoBacen(@Param("id") Integer id, @Param("codigoBacen") String codigoBacen);

    @Query("select p.id as id, p.nome as nome from Pais p order by p.nome asc")
    List<PaisView> buscaTodosPorIdNome();

}
