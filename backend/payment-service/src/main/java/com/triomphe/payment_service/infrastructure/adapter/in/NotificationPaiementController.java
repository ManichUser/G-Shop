package com.triomphe.payment_service.infrastructure.adapter.in;

import com.triomphe.payment_service.application.services.NotifierFournisseurService;
import com.triomphe.payment_service.domain.model.CommandeId;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationPaiementController {

    private final NotifierFournisseurService notifierFournisseurService;

    @PostMapping("/{commandeId}")
    public ResponseEntity<String> notifier(
            @PathVariable String commandeId,
            @RequestParam double montantAttendu,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateLimite
    ) {
        notifierFournisseurService.verifierEtNotifier(new CommandeId(commandeId), montantAttendu, dateLimite);
        return ResponseEntity.ok(" Notification traitée pour la commande " + commandeId);
    }
}
