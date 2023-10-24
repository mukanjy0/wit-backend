package com.example.wit.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface JwtService {
    String jwtSigningKey = "${my.secret}";
    Jwt jwt = new Jwt(jwtSigningKey);

    public String extractUserName(String token) {
        return jwt.extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return jwt.generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !jwt.isTokenExpired(token);
    }
}
