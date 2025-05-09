package com.tuempresa.portal.usuario.adapters.inbound.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.UUID;

@UtilityClass
public class JwtUtil {

    private static final String SECRET_KEY = "secreto_super_seguro";
    private static final long EXPIRATION_TIME = 86400000; // 1 día en ms

    public static String generateToken(UUID userId, String email) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static boolean validateToken(String token, UUID userId) {
        Claims claims = getClaims(token);
        return claims.getSubject().equals(userId.toString()) && claims.getExpiration().after(new Date());
    }
} 