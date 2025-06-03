package com.example.authentification_test.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("API d'authentification - projet de vente local")
                .description("Cette API permet l'enregistrement, la connexion, la gestion des roles et des acces des utilisateurs(FOURNISSEUR,ACHETEUR).")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("ByteMasterMind Team")
                                .email("dev@bytemastermind.cm"))
                        .license(new License()
                                .name("Licence UDs")
                                .url("https://fs.univ-dschang.org")));
    }
}
