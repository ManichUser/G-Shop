package com.example.authentification_test.security;

import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.entities.User;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRespository userRespository;

    // Ce filtre s'exécute à chaque requête HTTP pour vérifier la présence et la validité du token JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // On ignore les endpoints d'authentification
        if (request.getServletPath().startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Si aucun header ou mauvais format, on passe la requête sans traitement JWT
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); // "Bearer " fait 7 caractères

        try {
            Long userId = jwtUtil.extractUserId(token);

            // On vérifie que le token est valide et qu'aucune authentification n'est encore présente
            if (jwtUtil.isTokenValid(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRespository.findById(userId).orElse(null);

                if (user != null && user.isEnabled()) {
                    // Seul le rôle actif (currentRole) est utilisé dans le contexte
                    List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_" + user.getCurrentRole().name())
                    );

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            authorities
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Injection de l'utilisateur dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    System.out.println("Utilisateur authentifié : " + user.getEmail() +
                            " | Rôle actif : " + user.getCurrentRole());
                }
            }
        } catch (JwtException | IllegalArgumentException e) {
            // En cas d'erreur sur le token (expiré, mal formé...), on retourne 401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token JWT invalide : " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
