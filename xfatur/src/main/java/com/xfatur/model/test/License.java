package com.xfatur.model.test;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;

    @Temporal(TemporalType.DATE)
    private Date valid_from;

    @Temporal(TemporalType.DATE)
    private Date valid_to;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Date getValid_from() {
	return valid_from;
    }

    public void setValid_from(Date valid_from) {
	this.valid_from = valid_from;
    }

    public Date getValid_to() {
	return valid_to;
    }

    public void setValid_to(Date valid_to) {
	this.valid_to = valid_to;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

}
