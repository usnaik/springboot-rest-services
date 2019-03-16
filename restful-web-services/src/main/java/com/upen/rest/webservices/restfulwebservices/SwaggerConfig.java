package com.upen.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact(
			"Upendra Naik", "http://www.upendranaik.com", "upendra_naik@yahoo.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"RestFul API Documentation", "Restful API Description - Users", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());

	private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS = 
			new HashSet<>(Arrays.asList("application/json","application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCERS_AND_CONSUMERS)
				.consumes(DEFAULT_PRODUCERS_AND_CONSUMERS);
		
//				.select()                                  
//		        .apis(RequestHandlerSelectors.any())              
//		        .paths(PathSelectors.any())                          
//		        .build(); 
	}
}
