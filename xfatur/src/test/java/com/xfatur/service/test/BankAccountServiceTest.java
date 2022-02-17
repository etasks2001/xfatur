package com.xfatur.service.test;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class BankAccountServiceTest {

    @Autowired
    private BankAccountService service;

    // @Test
    @Order(1)
    void test_transfer() {
	service.transfer(500);
    }

}
