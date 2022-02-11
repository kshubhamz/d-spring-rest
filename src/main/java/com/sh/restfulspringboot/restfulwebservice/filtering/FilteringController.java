package com.sh.restfulspringboot.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filters")
	public SomeBean getBeans() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		return someBean;
	}
	
	@GetMapping("/filters-list")
	public List<SomeBean> getBeansList() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value11", "value12", "value13"));
	}
	
	@GetMapping("/dynamic-filters")
	public MappingJacksonValue getBeansDynaicFilters() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("bean1234", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
}
