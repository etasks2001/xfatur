package com.xfatur.service;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.testutil.UtilCreateProduto;

class ListaPrecoServiceTest {

    @Autowired
    UtilCreateProduto utilCreateProduto;

    @BeforeAll
    void insert() {
	List<Integer> idsProduto = utilCreateProduto.idsProduto();

    }

    @AfterAll
    void delete() {

    }

}
