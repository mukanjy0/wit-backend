package com.example.wit.auth.application;

import com.example.wit.auth.domain.AuthService;
import com.example.wit.auth.dto.JwtAuthenticationResponse;
import com.example.wit.entities.player.dto.PlayerSignIn;
import com.example.wit.entities.player.dto.PlayerSignUp;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@Valid @RequestBody PlayerSignUp player) {
        return new ResponseEntity<>(authService.signUp(player), HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody PlayerSignIn player) {
        return new ResponseEntity<>(authService.signIn(player), HttpStatus.OK);
    }
}
