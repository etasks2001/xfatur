package com.xfatur.repository.test;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.model.test.License;
import com.xfatur.model.test.Person;

//@SpringBootTest
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class LicenseRepositoryTest {
    @BeforeEach
    void mark_start() {
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @AfterEach
    void mark_end() {
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

    }

    @Autowired
    LicenseRepository licenseRepository;

    @Autowired
    PersonRepository personRepository;

    // @Test
    @Order(1)
    void test_create_from_license() {
	License license = new License();
	license.setType("CAR");
	license.setValid_from(new Date());
	license.setValid_to(new Date());

	Person person = new Person();
	person.setFirst_name("Heitor");
	person.setLast_name("Telles");
	person.setAge(35);
	person.setLicense(license);

//	license.setPerson(person);

	personRepository.save(person);
    }

    // @Test
    @Order(2)
    void test_create_from_person() {

	Person person = new Person();
	person.setFirst_name("Heitor");
	person.setLast_name("Telles");
	person.setAge(35);

	Person saved = personRepository.save(person);

	License license = new License();
	license.setType("BUSS");
	license.setValid_from(new Date());
	license.setValid_to(new Date());
	saved.setLicense(license);

	personRepository.save(saved);

    }

    // @Test
    @Order(3)
    void test_update() {
	Person person = personRepository.findById(1).get();

	License license = person.getLicense();
	license.setType("BUSSsssss");
	license.setValid_from(new Date());
	license.setValid_to(new Date());
	license.setPerson(person);

	licenseRepository.save(license);
    }
}