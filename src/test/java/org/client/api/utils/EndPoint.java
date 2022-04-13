package org.client.api.utils;

public enum EndPoint {
	BASEURL("https://reqres.in/api"), 
	Create("/users");

	private String value;

	EndPoint(String Attribute) {
		this.value = Attribute;
	}

	public String getAttribute() {
		return value;
	}

}
