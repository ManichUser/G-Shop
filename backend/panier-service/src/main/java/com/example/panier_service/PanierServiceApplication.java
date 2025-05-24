package com.example.panier_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PanierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanierServiceApplication.class, args);
	}

}
