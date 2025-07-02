package com.example.demo.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final long EXPIRATION    = 3600000;
    
    private static final String SECRET       = "fM8P3GhN+pLdTz/C5zDR9qHsCVz/6TX3ojUZxy5MzdM=";
    private static final byte[] SECRET_BYTES = Decoders.BASE64.decode(SECRET);

    public static String generateToken(String username, String role) {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(Keys.hmacShaKeyFor(SECRET_BYTES))
            .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET_BYTES))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
