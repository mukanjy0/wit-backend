package com.example.wit.entities.player.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Accessors(fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class PlayerSignUp {
    @Size(max = 24)
    @NotBlank
    private String username;
    private Integer points;
    private Integer rating;
    private Short currentCategory;
    private Short bestCategory;
    @NonNull
    private Short careerId;
    @NonNull
    private Short universityId;
    @NonNull
    private Long teamId;
}
