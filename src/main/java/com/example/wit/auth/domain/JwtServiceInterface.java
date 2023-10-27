package com.example.wit.auth.domain;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServiceInterface {

    public String extractUsername(String token);

    public String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
