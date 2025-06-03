// src/main/java/com/gshop/payment_service/config/ExternalApiConfig.java
package com.gshop.payment_service.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe de configuration pour les URLs des APIs externes (Orange Money, MTN MoMo).
 * Les propriétés sont chargées depuis application.yml et mappées à cette classe.
 */
@Component
@ConfigurationProperties(prefix = "") // Charge les propriétés sans préfixe spécifique (au niveau racine)
@Setter
@Getter
public class ExternalApiConfig {

    private Api orangeApi = new Api(); // Initialise un objet imbriqué pour orange-api
    private Api mtnApi = new Api();    // Initialise un objet imbriqué pour mtn-api

    // Classe interne pour structurer les propriétés de chaque API (ex: url)
    @Data
    public static class Api {
        private String url;

    }
}
