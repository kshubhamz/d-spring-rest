package com.sh.restfulspringboot.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	// end-point versioning
	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Shubham Kumar");
	}
	
	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Shubham", "Kumar"));
	}
	
	// query-param versioning
	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Shubham Kumar");
	}
	
	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Shubham", "Kumar"));
	}
	
	// header versioning
	@GetMapping(value = "person/header", headers = "X-API_VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Shubham Kumar");
	}
	
	@GetMapping(value = "person/header", headers = "X-API_VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Shubham", "Kumar"));
	}
	
	// using accept in header
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Shubham Kumar");
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Shubham", "Kumar"));
	}
}
