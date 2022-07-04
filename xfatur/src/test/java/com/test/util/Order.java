package com.test.util;

public enum Order {

    ASC("asc"), DESC("desc");

    private String order;

    private Order(String order) {
	this.order = order;
    }

    public String getOrder() {
	return order;
    }
}
