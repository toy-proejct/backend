package com.example.backend.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    private static final String CLAIM_EMAIL = "email";
    private static final SecretKey signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Long TWELVE_HOURS_IN_MILLISECONDS = 1000 * 60 * 60 * 12L;

    private Long expirationInMilliseconds = TWELVE_HOURS_IN_MILLISECONDS;

    public JwtService() {
    }

    public JwtService(Long expirationInMilliseconds) {
        this.expirationInMilliseconds = expirationInMilliseconds;
    }

    public String createToken(String payload) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationInMilliseconds);

        return Jwts.builder()
                .claim(CLAIM_EMAIL, payload)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(signingKey)
                .compact();
    }

    public String getClaimEmail(String token) {
        if (!isValidToken(token)) {
            return "";
        }

        return getClaimsJws(token).getBody()
                .get(CLAIM_EMAIL, String.class);
    }

    public boolean isValidToken(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey.getEncoded())
                .build()
                .parseClaimsJws(token);
    }
}
