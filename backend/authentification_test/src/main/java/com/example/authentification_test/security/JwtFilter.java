package com.example.authentification_test.security;

import com.example.authentification_test.Respository.UserRespository;
import com.example.authentification_test.entities.User;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRespository userRespository;

    //Filtage de chaque requetr HTTP pour verifier le token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        try {
            Long userId = jwtUtil.extractUSerId(token);
            if(jwtUtil.isTokenValid(token) && SecurityContextHolder.getContext().getAuthentication()==null){
                User user = userRespository.findById(userId).orElse(null);

                if (user != null && user.isEnabled()){
                    //inject l'utilisateur dans le contexte de spring security
                    UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(user,
                            null,
                            Collections.emptyList());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Erreur JWT:" + e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
