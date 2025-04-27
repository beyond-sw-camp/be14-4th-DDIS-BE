package com.DDIS.security.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret;
    private final long expiration;

    public JwtUtil(@Value("${token.secret}") String secret,
                   @Value("${token.expiration_time}") long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(String clientId, String role) {
        return Jwts.builder()
                .setSubject(clientId)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getClientId(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}