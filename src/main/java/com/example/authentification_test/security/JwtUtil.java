package com.example.authentification_test.security;


import com.example.authentification_test.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import  java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private Key secretkey;

    //Duree de validite d'un token
    private  final long expirationMs = 24*60*60*1000;

    @PostConstruct
    public void init(){
        this.secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    //Genere un token contenant l'id et le role actif
    public String generateToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("role",user.getCurrentRole().name());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ expirationMs) ;

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretkey)
                .compact();
    }
    //recupere l'id du user depuis le token
    public Long extractUserId(String token){
        String subject = Jwts.parserBuilder()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.parseLong(subject);
    }
    //recupere le role
    public String extractRole(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role",String.class);
    }

    //verifie si le token est expirer ou non

    public boolean isTokenValid(String token){
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(secretkey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();

            return  expiration.after(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
