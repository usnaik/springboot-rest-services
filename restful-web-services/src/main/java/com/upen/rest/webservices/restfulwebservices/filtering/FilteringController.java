package com.upen.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean( "value1","value2","value3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveSomeBeanList() {
		return Arrays.asList(new SomeBean( "test1","test2","test3"), 
				new SomeBean( "test21","test22","test23"));
	}

}
