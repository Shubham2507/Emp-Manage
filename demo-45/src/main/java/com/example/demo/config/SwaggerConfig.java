package com.example.demo.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
	 @Bean
	    public Docket api() { 
	        		return new Docket(DocumentationType.SWAGGER_2)
	        				.groupName("public-api")
	        				.apiInfo(apiInfo())
	        				.select()
	        				
	        				.paths(path())
	        				.build();                                         
	    }
	 
	 @SuppressWarnings("unchecked")
	Predicate<String> path() {
			return or(regex("/.*"), regex("/v1/.*"), regex("/login"), regex("/signup"), regex("/logOut"), regex("/showAll"));
		}
	 private ApiInfo apiInfo() {
	    	return new ApiInfo(
	    			"Employee Management",
	    		    "API to save Employee details", 
	    		    "1.0",
	    		    "Terms of service",
	    		    new Contact("Kumar Shubham", "https://github.com/Shubham2507/Emp-Manage", "kumar.shubham@infogain.com"), 
	    		    "Infogain Trainee License 1.0", "https://www.infogain.com");
	    }
	    
}