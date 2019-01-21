package com.doorcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DoorcheckApplication /* extended for war */ extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DoorcheckApplication.class, args);
	}
	
	/** added for war */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DoorcheckApplication.class);
	}

}

