package com.xfatur.repository.test;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.model.test.Customer;
import com.xfatur.model.test.PhoneNumber;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class CustumerRepositoryTest {

    @Autowired
//    CustomerRepository repository;

    // //@Test
    @Order(1)
    void test_cria_customer() {
	Customer customer = new Customer();
	customer.setName("Heitor");

	PhoneNumber phoneNumber1 = new PhoneNumber();
	phoneNumber1.setNumber("7489789");
	phoneNumber1.setType("work");

	PhoneNumber phoneNumber2 = new PhoneNumber();
	phoneNumber2.setNumber("15935489");
	phoneNumber2.setType("home");

	customer.addPhoneNumber(phoneNumber1);
	customer.addPhoneNumber(phoneNumber2);

//	repository.save(customer);
    }

    // //@Test
    @Transactional
    @Order(2)
    void test_load_customer() {
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//	Customer customer = repository.findById(1).get();
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//	System.out.println(customer.getName());

//	Set<PhoneNumber> phoneNumbers = customer.getPhoneNumbers();
//	phoneNumbers.forEach(phoneNumber -> System.out.println(phoneNumber.getNumber()));
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

//    //@Test
    @Transactional
    @Order(3)
    void test_update_customer() {
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//	Customer customer = repository.findById(1).get();
//	customer.setName("Heitor Augusto");
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

//	Set<PhoneNumber> phoneNumbers = customer.getPhoneNumbers();

//	phoneNumbers.forEach(pn -> {
//	    if (pn.getId() == 1) {
//		pn.setNumber("55 11 3333-4444");
//		pn.setType("cell");
//	    } else if (pn.getId() == 2) {
//		pn.setNumber("55 11 6666-9999");
//		pn.setType("school");
//	    }
//	});
//	if (phoneNumbers.size() == 0) {
//	    PhoneNumber phoneNumber = new PhoneNumber();
//	    phoneNumber.setNumber("55 11 3333-4444");
//	    phoneNumber.setType("cell");
//
//	    customer.addPhoneNumber(phoneNumber);
//
//	}
//	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//	repository.save(customer);
//	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
//
//	//@Test
//	void test_delete() {
//		repository.deleteById(1);
//	}

}