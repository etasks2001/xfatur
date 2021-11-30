package com.xfatur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xfatur.model.Selo;

public interface SeloRepository extends JpaRepository<Selo, Integer> {

    List<Selo> findAllByNumeroGuiaContains(String numeroGuia);

}
