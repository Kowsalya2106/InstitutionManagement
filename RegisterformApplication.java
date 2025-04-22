package com.example.Registerform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.example.UserController", "com.example.Registerform" })
@EntityScan(basePackageClasses = {RegisterformApplication.class})
public class RegisterformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterformApplication.class, args);
	}

}
