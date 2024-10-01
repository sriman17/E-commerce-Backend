package com.task.ShopSpring;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;


@SpringBootApplication(exclude = { ReactiveSecurityAutoConfiguration.class, org.springframework.boot.actuate.autoconfigure.security.reactive.ReactiveManagementWebSecurityAutoConfiguration.class })
public class ShopSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopSpringApplication.class, args);
	}
	
}
