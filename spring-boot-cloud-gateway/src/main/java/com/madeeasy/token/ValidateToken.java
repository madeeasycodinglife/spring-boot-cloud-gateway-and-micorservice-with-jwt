package com.madeeasy.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class ValidateToken {

    private final static String SECRET_KEY = "68746846846546843168434684316843546843461684684468416846341646468468464684";

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String getUserName(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDate(token);
        return expirationDate.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token) {
        String userName = getUserName(token);
        return  !isTokenExpired(token);
    }
}
