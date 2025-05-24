package com.example.panier_service.service;



import com.example.panier_service.dto.CommandeRequestDTO;
import com.example.panier_service.dto.CommandeResponseDTO;
import com.example.panier_service.model.CommandeStatus;

import java.util.List;

public interface CommandeService {

    CommandeResponseDTO createCommande(CommandeRequestDTO request);

    List<CommandeResponseDTO> getCommandesByUserId(String userId);

    List<CommandeResponseDTO> getCommandesByProductId(String productId);

    void deleteCommande(String id);
    
    List<CommandeResponseDTO> getAllCommandes();
    CommandeResponseDTO updateStatus(String id, CommandeStatus status);


}
