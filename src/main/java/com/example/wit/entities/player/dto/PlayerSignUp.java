package com.example.wit.entities.player.dto;

import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.domain.role.Role;
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
    @NotBlank
    private String password;
    private Integer points;
    @NotNull
    private Integer rating;
    private Short currentCategoryId;
    private String avatarUrl;
    private String role;
    @NotNull
    private Long teamId;
    @NotNull
    private Short careerId;
    @NotNull
    private Short universityId;
}
