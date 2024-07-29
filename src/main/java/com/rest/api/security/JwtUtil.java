package com.rest.api.security;

import com.rest.api.dto.AuthTokenDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public String createToken(AuthTokenDetails authTokenDetails) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authTokenDetails.id());
        claims.put("username", authTokenDetails.username());
        claims.put("email", authTokenDetails.email());
        claims.put("contactNo", authTokenDetails.contactNo());
        claims.put("city", authTokenDetails.city());
        claims.put("state", authTokenDetails.state());
        claims.put("country", authTokenDetails.country());
        claims.put("isAccountNonExpired", authTokenDetails.isAccountNonExpired());
        claims.put("isAccountNonLocked", authTokenDetails.isAccountNonLocked());
        claims.put("isCredentialsNonExpired", authTokenDetails.isCredentialsNonExpired());
        claims.put("isEnabled", authTokenDetails.isEnabled());
        claims.put("verified", authTokenDetails.verified());
        claims.put("roles", authTokenDetails.roles());
        claims.put("authorities", authTokenDetails.authorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(authTokenDetails.username())
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }


    public Claims verifyToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        return verifyToken(token) != null;
    }

    public AuthTokenDetails getAuthTokenDetailsFromToken(String token) {
        Claims claims = verifyToken(token);
        if (claims == null) return null;
        return new AuthTokenDetails(
                (String) claims.get("id"),
                (String) claims.get("username"),
                (String) claims.get("email"),
                (String) claims.get("contactNo"),
                (String) claims.get("city"),
                (String) claims.get("state"),
                (String) claims.get("country"),
                (Boolean) claims.get("isAccountNonExpired"),
                (Boolean) claims.get("isAccountNonLocked"),
                (Boolean) claims.get("isCredentialsNonExpired"),
                (Boolean) claims.get("isEnabled"),
                (Boolean) claims.get("verified"),
                (List<String>) claims.get("roles"),
                (List<String>) claims.get("authorities")
        );
    }
}


