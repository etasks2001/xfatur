package com.xfatur.repository.preco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xfatur.model.preco.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select i from Item i where i.lista.id = :id order by i.produto asc")
    List<Item> findItensById(@Param("id") Integer id);

}
