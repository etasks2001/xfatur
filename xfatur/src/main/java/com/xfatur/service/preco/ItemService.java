package com.xfatur.service.preco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.preco.Item;
import com.xfatur.repository.preco.ItemRepository;

@Service
@Transactional(readOnly = true)
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findItensById(Integer id) {
	return repository.findItensById(id);
    }

}
