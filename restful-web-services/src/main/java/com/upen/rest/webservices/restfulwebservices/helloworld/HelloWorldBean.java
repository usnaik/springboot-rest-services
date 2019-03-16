package com.upen.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	
	private String message;

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	public HelloWorldBean(String string) {
		this.setMessage(string);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
