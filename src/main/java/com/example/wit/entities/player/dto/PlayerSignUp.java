package com.example.wit.entities.player.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter @RequiredArgsConstructor
public class PlayerSignUp {
    @Size(max = 24)
    @NotBlank
    final private String username;
    final private Integer points;
    final private Integer rating;
    final private Short currentCategory;
    final private Short bestCategory;
    @NonNull
    final private Short careerId;
    @NonNull
    final private Short universityId;
    @NonNull
    final private Long teamId;
}
