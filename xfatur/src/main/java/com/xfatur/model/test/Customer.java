package com.xfatur.model.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    /**
     * O mapeamento pertence a Customer Primary Key está no customer
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PhoneNumber> phoneNumbers;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
	if (phoneNumber != null) {
	    if (phoneNumbers == null) {
		phoneNumbers = new HashSet<PhoneNumber>();
	    }
	    phoneNumber.setCustomer(this);
	    phoneNumbers.add(phoneNumber);
	}
    }
}
