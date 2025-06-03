package com.example.authentification_test.service;

import com.example.authentification_test.Respository.ProducerDetailsRespository;
import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.dto.AuthResponse;
import com.example.authentification_test.dto.LoginRequest;
import com.example.authentification_test.dto.ProducerRegisterRequest;
import com.example.authentification_test.dto.RegisterRequest;
import com.example.authentification_test.entities.ProducerDetails;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.entities.User;
import com.example.authentification_test.security.JwtUtil;
import com.example.authentification_test.utils.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final List<String> VALID_AREAS = List.of(
            "Garoua", "Bafoussam", "Yaounde", "Douala", "Bertoua",
            "Ngaoundere", "Buea", "Bamenda", "Ebolowa", "Maroua", "Dschang", "Mbouda"
    );

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final ProducerDetailsRespository producerDetailsRespository;
    private final JwtUtil jwtUtil;

    // Enregistre un nouvel utilisateur
    public AuthResponse registerUser(RegisterRequest request) {
        if (userRespository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .adresse(request.getAdress())
                .enabled(true)
                .currentRole(Role.USER)
                .availableRoles(Set.of(Role.USER))
                .build();

        userRespository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getCurrentRole());
    }

    // Authentifie un utilisateur
    public AuthResponse authenticateUser(LoginRequest request) {
        User user = userRespository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email ou mot de passe incorrect."));

        if (!user.isEnabled()) {
            throw new IllegalArgumentException("Ce compte est désactivé.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect.");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getCurrentRole());
    }

    // Change de rôle actif
    public AuthResponse switchRole(Long userId, Role newRole) {
        User user = userRespository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));

        if (!user.hasRole(newRole)) {
            throw new IllegalArgumentException("Ce rôle n'est pas disponible.");
        }

        user.setCurrentRole(newRole);
        userRespository.save(user);

        String newToken = jwtUtil.generateToken(user);
        return new AuthResponse(newToken, newRole);
    }

    // Convertit un utilisateur normal en fournisseur
    public void ConvertToProducer(User user, ProducerRegisterRequest request) {
        if (request.isHasMTN() && !PhoneValidator.isValidPhoneNumber(request.getMtnNumber(), "MTN")) {
            throw new IllegalArgumentException("Numéro MTN invalide");
        }
        if (request.isHasORANGE() && !PhoneValidator.isValidPhoneNumber(request.getOrangeNumber(), "ORANGE")) {
            throw new IllegalArgumentException("Numéro ORANGE invalide");
        }
        if (!areValidAreas(request.getDeliveryZones())) {
            throw new IllegalArgumentException("Zone de livraison invalide");
        }

        ProducerDetails details = ProducerDetails.builder()
                .companyName(request.getCompanyName())
                .registrationNumber(request.getRegistrationNumber())
                .companyAdress(request.getCompanyAdress())
                .managerName(request.getManagerName())
                .managerPhone(request.getManagerPhone())
                .managerEmail(request.getManagerEmail())
                .identityType(request.getIdentityType())
                .identityNumber(request.getIdentityNumber())
                .hasMTN(request.isHasMTN())
                .mtnNumber(request.getMtnNumber())
                .hasORANGE(request.isHasORANGE())
                .orangeNumber(request.getOrangeNumber())
                .productCategories(request.getProductCategories())
                .averageStock(request.getAverageStock())
                .deliveryZones(request.getDeliveryZones())
                .acceptReturn(request.isAcceptReturn())
                .hasCustomersService(request.isHasCustomersService())
                .serviceHours(request.getServiceHours())
                .user(user)
                .build();

        producerDetailsRespository.save(details);

        if (!user.getAvailableRoles().contains(Role.PRODUCER)) {
            user.getAvailableRoles().add(Role.PRODUCER);
        }

        user.setCurrentRole(Role.PRODUCER);
        userRespository.save(user);
    }

    private boolean areValidAreas(List<String> zones) {
        return zones != null && zones.stream().allMatch(area ->
                VALID_AREAS.stream().anyMatch(valid -> valid.equalsIgnoreCase(area)));
    }
}
