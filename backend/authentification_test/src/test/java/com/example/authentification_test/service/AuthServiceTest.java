package com.example.authentification_test.service;

import com.example.authentification_test.Respository.ProducerDetailsRespository;
import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.dto.AuthResponse;
import com.example.authentification_test.dto.ProducerRegisterRequest;
import com.example.authentification_test.dto.RegisterRequest;
import com.example.authentification_test.entities.Role;
import com.example.authentification_test.entities.User;
import com.example.authentification_test.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) class AuthServiceTest {

    @Mock
    private UserRespository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ProducerDetailsRespository producerDetailsRespository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    void registerUser_shouldCreateUser() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("secret");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhoneNumber("699000000");
        request.setAdress("Dschang");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtUtil.generateToken(any())).thenReturn("jwtToken");

        AuthResponse response = authService.registerUser(request);

        assertEquals("jwtToken", response.getAccessToken());
        assertEquals(Role.USER, response.getCurrentRole());
    }

    @Test
    void registerUser_shouldThrowIfEmailExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("exist@example.com");

        when(userRepository.findByEmail("exist@example.com")).thenReturn(Optional.of(new User()));

        assertThrows(IllegalArgumentException.class, () -> authService.registerUser(request));
    }

    @Test
    void switchRole_shouldChangeCurrentRole() {
        User user = User.builder()
                .id(1L)
                .email("user@test.com")
                .enabled(true)
                .currentRole(Role.USER)
                .availableRoles(Set.of(Role.USER, Role.PRODUCER))
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(any())).thenReturn("newToken");

        AuthResponse response = authService.switchRole(1L, Role.PRODUCER);

        assertEquals("newToken", response.getAccessToken());
        assertEquals(Role.PRODUCER, response.getCurrentRole());
    }

    @Test
    void convertToProducer_shouldAddProducerRole() {
        User user = User.builder()
                .id(1L)
                .email("prod@test.com")
                .enabled(true)
                .currentRole(Role.USER)
                .availableRoles(Set.of(Role.USER))
                .build();

        ProducerRegisterRequest request = new ProducerRegisterRequest();
        request.setCompanyName("Entreprise X");
        request.setRegistrationNumber("RC123");
        request.setCompanyAdress("Yaoundé");
        request.setManagerName("Mr. X");
        request.setManagerPhone("690000000");
        request.setManagerEmail("x@example.com");
        request.setIdentityType("CNI");
        request.setIdentityNumber("123456789");
        request.setHasMTN(false);
        request.setHasORANGE(false);
        request.setDeliveryZones(List.of("Yaounde"));
        request.setProductCategories("Agroalimentaire");
        request.setAverageStock("500");
        request.setAcceptReturn(true);
        request.setHasCustomersService(true);
        request.setServiceHours("8h - 16h");

        authService.ConvertToProducer(user, request);

        assertTrue(user.getAvailableRoles().contains(Role.PRODUCER));
        assertEquals(Role.PRODUCER, user.getCurrentRole());
    }

}

