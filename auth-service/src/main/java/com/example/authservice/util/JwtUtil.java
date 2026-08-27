package com.example.authservice.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.security.SignatureException;
import java.util.Base64;
import java.util.Date;

@Component // this Registers this class as a java bean or spring bean so that the spring knows which class to auto-wire using dependency injection.
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret ){
        byte[] keyBytes  = Base64.getDecoder().decode(secret.getBytes(
                StandardCharsets.UTF_8
        ));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role){
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*10)) // 10 hrs
                .signWith(secretKey)
                .compact();  // compact will convert everything which comes
                // from above chaining to a String;

    }

    public void validateToken(String token){
        try{
            Jwts.parser().verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token);
        }catch(SignatureException e){
            throw new JwtException("Invalid JWT exception");
        }catch(JwtException e){
            throw new JwtException("Invalid JWT");
        }
    }
}
