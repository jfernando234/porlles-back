package com.example.sbootporlles.config;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
    public final static String ACCESS_TOKEN_SECRET="4qhq8LrEBfYcaRHxhdb9zURb2rf8e7UdPorllesSecretKey";
    public final static Long ACCESS_TOKEN_VALIDITY_SECONDS=86400L; // 24 horas

    public static String createToken(String nombre, String correo){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(correo)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                        .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            
            String correo = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(correo, null, Collections.emptyList());
        }
        catch(JwtException ex){
            return null;
        }
    }
}
