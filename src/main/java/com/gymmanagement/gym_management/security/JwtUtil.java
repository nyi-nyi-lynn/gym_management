package com.gymmanagement.gym_management.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private final String SECRET = "thisiaverylongandsecuresecretkeyforgymmanagement2026";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 ;

    public String generateToken(String eamil, String role){
        return Jwts.builder()
        .setSubject(eamil)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256,SECRET)
        .compact();
    }

    public String extractEmail(String token){
        return Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }

    public String extractRole(String token){
        return Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .get("role", String.class);
    }
}
