package com.example.wit.entities.player.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerSignUp {
    @Size(max = 24)
    @NotBlank
    private String username;
    private Integer points;
    private Integer rating;
    private Short currentCategory;
    private Short bestCategory;
    @NotNull
    private Short careerId;
    @NotNull
    private Short universityId;
    @NotNull
    private Long teamId;
}
