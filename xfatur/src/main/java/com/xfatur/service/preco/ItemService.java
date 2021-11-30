package com.xfatur.service.preco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfatur.repository.preco.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;
}
