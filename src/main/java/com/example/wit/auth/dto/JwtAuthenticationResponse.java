package com.example.wit.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class JwtAuthenticationResponse {
    @NotBlank
    private String token;
}
