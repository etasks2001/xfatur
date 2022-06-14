package com.xfatur.repository.security;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.security.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select distinct u from Usuario u join u.perfis p where u.email = :email")
    Usuario findByEmail(@Param("email") String email);

    @Query("select distinct u from Usuario u join u.perfis p where u.email like :search% or p.desc like :search%")
    Page<Usuario> findPorEmailOrPerfil(String search, Pageable pageable);

    @Query("select distinct u from Usuario u join u.perfis p where u.id = :usuarioId and p.id in :perfisId")
    Optional<Usuario> findByIdAndPerfis(@Param("usuarioId") Integer usuarioId, @Param("perfisId") Integer[] perfisId);

    @Query("select u from Usuario u where u.email like :email and u.ativo=true ")
    Optional<Usuario> findByEmailAndAtivo(String email);

}
