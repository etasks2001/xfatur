package com.xfatur.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.xfatur.model.test.EnderecoCobranca;
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class EnderecoCobrancaServiceTest {

    static Stream<EnderecoCobranca> model() {
	return Stream.of(CreateModelTest.createEnderecoCobranca1(), CreateModelTest.createEnderecoCobranca2());
    }

    @Test
    void test() {

    }

}
