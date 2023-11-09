package com.example.wit.entities.player.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerResponse {
    private Long id;
    private String username;
    private Integer points;
    private Integer rating;
    private Short bestCategoryId;
    private Short currentCategoryId;
    private String avatarUrl;
    private LocalDate registrationDate;
    private Long teamId;
    private Short careerId;
    private Short universityId;
}
