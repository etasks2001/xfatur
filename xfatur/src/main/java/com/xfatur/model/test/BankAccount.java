package com.xfatur.model.test;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {

    @Id
    private Integer accno;
    private String lastname;
    private String firstname;
    private Integer bal;

    public Integer getAccno() {
	return accno;
    }

    public void setAccno(Integer accno) {
	this.accno = accno;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public Integer getBal() {
	return bal;
    }

    public void setBal(Integer bal) {
	this.bal = bal;
    }

    @Override
    public String toString() {
	return "BankAccount [accno=" + accno + ", lastname=" + lastname + ", firstname=" + firstname + ", bal=" + bal + "]";
    }

}
