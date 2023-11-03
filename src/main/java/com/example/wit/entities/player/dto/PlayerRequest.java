package com.example.wit.entities.player.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerUpdate {
    @Size(max = 24)
    private String username;
    private Integer points;
    private Integer rating;
    private Short currentCategoryId;
    @Size(max = 255)
    private String avatarUrl;
    private Long teamId;
    private Short careerId;
    private Short universityId;
}
