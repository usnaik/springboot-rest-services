package com.upen.rest.webservices.restfulwebservices.filtering;

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

	//Dynamic Filter - field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		
		SomeBean someBean= new SomeBean( "value1","value2","value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1","field2");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}

	//Dynamic Filter - field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeBeanList() {

		List<SomeBean> list = Arrays.asList(new SomeBean( "test1","test2","test3"), 
				new SomeBean( "test21","test22","test23"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field2","field3");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}

}
