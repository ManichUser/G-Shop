package com.triomphe.payment_service.application.services;

import com.triomphe.payment_service.application.port.out.NotificationPort;
import com.triomphe.payment_service.application.port.out.PaiementRepository;
import com.triomphe.payment_service.domain.model.CommandeId;
import com.triomphe.payment_service.domain.model.Paiement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotifierFournisseurService {

    private final PaiementRepository paiementRepository;
    private final NotificationPort notificationPort;

    public void verifierEtNotifier(CommandeId commandeId, double montantAttendu, LocalDateTime dateLimiteLivraison) {

        List<Paiement> paiements = paiementRepository.findAllByCommandeId(commandeId);
        double totalCollecte = paiements.stream().mapToDouble(Paiement::getMontant).sum();
        boolean dateDepassee = LocalDateTime.now().isAfter(dateLimiteLivraison);

        if (totalCollecte>= montantAttendu || dateDepassee) {
            String message = dateDepassee
                    ? " Date limite atteinte pour la commande " + commandeId.getId() + ", veuillez livrer."
                    : " Montant total atteint pour la commande " + commandeId.getId() + ", livraison attendue.";

            notificationPort.envoyerNotification("grossiste@fournisseur.com", message);
        } else {
            System.out.println("🕒 Aucun critère de notification atteint pour la commande " + commandeId.getId());
        }
    }
}
