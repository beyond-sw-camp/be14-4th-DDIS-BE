package com.DDIS.security.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;


// JWT 생성/ 검증 도구

@Component
public class JwtUtil {

    private final String secret;
    private final long expiration;

    // 토큰 유효 시간 설정
    public JwtUtil(@Value("${token.secret}") String secret,
                   @Value("${token.expiration_time}") long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    // 토큰 생성 메서드
    public String generateToken(String clientId, String role) {
        return Jwts.builder()
                .setSubject(clientId)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰에서 사용자 ID 추출
    public String getClientId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}