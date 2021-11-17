package com.xfatur.repository.test;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xfatur.model.test.Customer;
import com.xfatur.model.test.PhoneNumber;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class CustumerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    @Order(1)
    void test_cria_customer() {
	Customer customer = new Customer();
	customer.setName("Heitor");

	PhoneNumber phoneNumber1 = new PhoneNumber();
	phoneNumber1.setNumber("7489789");
	phoneNumber1.setType("cell");

	PhoneNumber phoneNumber2 = new PhoneNumber();
	phoneNumber2.setNumber("15935489");
	phoneNumber2.setType("home");

	customer.addPhoneNumber(phoneNumber1);
	customer.addPhoneNumber(phoneNumber2);

	repository.save(customer);

    }

    @Test
    @Order(2)
    void test_altera_customer() {
	Customer customer = repository.findById(1).get();

	customer.setName(customer.getName() + "888");

	repository.save(customer);
    }
}