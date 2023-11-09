package com.example.wit.entities.player.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlayerRequest {
    @Size(max = 24)
    private String username;
    @Size(max = 255)
    private String email;
    private String password;
    private Integer points;
    private Integer rating;
    private Short currentCategoryId;
    private String avatarUrl;
    private String role;
    private Long teamId;
    private Short careerId;
    private Short universityId;
}
