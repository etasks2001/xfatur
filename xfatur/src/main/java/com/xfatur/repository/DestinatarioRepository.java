package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.Destinatario;

public interface DestinatarioRepository extends CrudRepository<Destinatario, Integer> {

	@Query("select d from Destinatario d where d.xNome like %:nome% order by d.xNome asc, d.CNPJCPF asc")
	List<Destinatario> buscaPorNome(@Param("nome") String nome);

	@Query("select d from Destinatario d where d.CNPJCPF = :cnpjcpf order by d.xNome asc, d.CNPJCPF asc")
	Destinatario buscaPorCNPJCPF(@Param("cnpjcpf") String cnpjcpf);

	@Query("select d from Destinatario d where d.id =:id")
	Destinatario buscaPorIdComEntrega(@Param("id") Integer id);

}
