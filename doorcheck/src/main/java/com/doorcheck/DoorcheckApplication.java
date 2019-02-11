package com.doorcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
@ComponentScan(basePackageClasses = DoorController.class)
public class DoorcheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorcheckApplication.class, args);
	}

}

