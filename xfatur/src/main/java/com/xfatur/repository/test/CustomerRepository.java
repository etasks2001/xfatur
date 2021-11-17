package com.xfatur.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xfatur.model.test.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
