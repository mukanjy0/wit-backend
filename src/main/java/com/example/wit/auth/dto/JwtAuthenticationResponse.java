package com.example.wit.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class JwtAuthenticationResponse {
    private Long id;
    private String token;
}
