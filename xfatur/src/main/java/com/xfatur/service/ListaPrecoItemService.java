package com.xfatur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.repository.ListaPrecoItemRepository;

@Service
public class ListaPrecoItemService {

    @Autowired
    private ListaPrecoItemRepository repository;
}
