package com.example.wit.auth;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

@Getter @NoArgsConstructor @AllArgsConstructor
public class JwtService implements JwtServiceInterface {
    public String extractUsername(String token) {
        return jwt.extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return jwt.generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !jwt.isTokenExpired(token);
    }
}
