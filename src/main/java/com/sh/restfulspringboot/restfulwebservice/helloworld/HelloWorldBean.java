package com.sh.restfulspringboot.restfulwebservice.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.setMessage(message);
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
