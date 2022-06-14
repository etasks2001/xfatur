package com.xfatur.validation.dto.security;

public class PerfilDTO {
    private Integer id;

    private String desc;

    public PerfilDTO() {
	super();
    }

    public PerfilDTO(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }

}
