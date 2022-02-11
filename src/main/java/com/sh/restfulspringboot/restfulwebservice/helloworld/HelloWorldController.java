package com.sh.restfulspringboot.restfulwebservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String getHelloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World from " + name);
	}

	@GetMapping(path = "/hello-world-lan")
	public String getHelloWorldIndustralized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("GREET", null, "Hello World", locale);
	}

}
