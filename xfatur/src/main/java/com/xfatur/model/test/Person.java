package com.xfatur.model.test;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String first_name;
    private String last_name;
    private Integer age;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private License license;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getFirst_name() {
	return first_name;
    }

    public void setFirst_name(String first_name) {
	this.first_name = first_name;
    }

    public String getLast_name() {
	return last_name;
    }

    public void setLast_name(String last_name) {
	this.last_name = last_name;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public License getLicense() {
	return license;
    }

    public void setLicense(License license) {
	this.license = license;
	this.license.setPerson(this);
    }

}
