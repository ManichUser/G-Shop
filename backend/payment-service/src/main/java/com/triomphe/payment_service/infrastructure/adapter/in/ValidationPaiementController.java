package com.triomphe.payment_service.infrastructure.adapter.in;

import com.triomphe.payment_service.application.dto.ValidationPaiementDTO;
import com.triomphe.payment_service.application.port.in.ValidationPaiementFournisseurUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class ValidationPaiementController {

    private final ValidationPaiementFournisseurUseCase useCase;

    @PostMapping("/valider")
    public ResponseEntity<Void> validerPaiement(@RequestBody ValidationPaiementDTO dto) {
        useCase.validerPaiement(dto);
        return ResponseEntity.ok().build();
    }
}
