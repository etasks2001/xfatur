package com.xfatur.repository.test;

import org.springframework.data.repository.CrudRepository;

import com.xfatur.model.test.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
