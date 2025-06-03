package com.gshop.payment_service.model;

import com.gshop.payment_service.model.enums.PaymentStatusEnum;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité JPA représentant un enregistrement de paiement dans la base de données.
 * C'est le modèle de données central pour toutes les transactions.
 */
@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id // Marque ce champ comme la clé primaire de l'entité
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Utilisation de UUID pour des IDs uniques et distribués

    // Référence unique fournie par le système appelant
    @Column(nullable = false, unique = true) // Assure que la référence est non nulle et unique
    private String externalOrderId;

    // Montant total du paiement
    @Column(nullable = false)
    private Double amount;

    // Devise du paiement (ex: "XAF", "EUR")
    @Column(nullable = false, length = 3)
    private String currency;

    // Méthode de paiement utilisée (ex: "ORANGE_MONEY", "MTN_MOMO").
    @Column(nullable = false)
    private String paymentMethod;

    // Statut actuel du paiement (PENDING, SUCCESS, FAILED, etc.)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum status;

    // Référence de la transaction chez le fournisseur de paiement externe (ex: ID de transaction Orange Money)
    @Column(unique = true)
    private String externalTransactionId;

    // Numéro de téléphone ou autres identifiants du client/acteur source
    @Column(nullable = false)
    private String payerIdentifier; // Ex: numéro de téléphone pour Mobile Money, ID client pour carte

    // Numéro de téléphone ou autres identifiants du client/acteur ..
    private String payeeIdentifier;

    // Description du paiement (pour les relevés ou l'audit)
    private String description;

    // URL où le fournisseur de paiement doit renvoyer le statut (webhook)
    private String callbackUrl;

    // Horodatage de la création de l'enregistrement de paiement
    @Column(nullable = false, updatable = false) // Ne doit pas être mis à jour après la création
    private LocalDateTime createdAt;

    // Horodatage de la dernière mise à jour de l'enregistrement de paiement
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Méthode exécutée avant la persistance de l'entité pour définir les horodatages initiaux
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Méthode exécutée avant la mise à jour de l'entité pour mettre à jour l'horodatage
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
