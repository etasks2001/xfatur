package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.repository.ListaPrecoRepository;

@Service
public class ListaPrecoService {

    @Autowired
    private ListaPrecoRepository repository;

}
