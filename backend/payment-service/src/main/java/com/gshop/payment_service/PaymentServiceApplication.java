package com.gshop.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Annotation Spring Boot qui active l'auto-configuration et le scan des composants
public class PaymentServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PaymentServiceApplication.class, args);
	}
}
