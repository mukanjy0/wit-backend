package com.example.wit.auth;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface JwtServiceInterface {
    String jwtSigningKey = "${my.secret}";
    Jwt jwt = new Jwt(jwtSigningKey);

    public String extractUsername(String token);

    public String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
