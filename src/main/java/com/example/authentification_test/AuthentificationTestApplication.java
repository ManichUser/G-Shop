package com.example.authentification_test;

import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@SpringBootApplication
public class AuthentificationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationTestApplication.class, args);
	}
	@Bean
	CommandLineRunner
	initAdmin(UserRespository userRespository, PasswordEncoder passwordEncoder){
		return args -> {
			String adminEmail = "bytemastermind@gmail.com";
			if(userRespository.findByEmail(adminEmail).isEmpty()){
				User admin = User.builder()
						.firstName("Super")
						.lastName("Admin")
						.email(adminEmail)
						.password(passwordEncoder.encode("admin1234"))
						.phoneNumber("659167414")
						.adresse("uds")
						.enabled(true)
						.currentRole(Role.ADMIN)
						.availableRoles(Set.of(Role.ADMIN))
						.build();
				userRespository.save(admin);
				System.out.println("Admin created");
			}
		};
	}

}
