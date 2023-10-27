package com.example.wit.entities.player.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerSignIn {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
