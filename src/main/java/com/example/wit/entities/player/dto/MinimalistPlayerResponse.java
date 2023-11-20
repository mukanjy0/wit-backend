package com.example.wit.entities.player.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class MinimalistPlayerResponse {
    private String username;
    private Integer points;
    private Integer rating;
    private String categoryTag;
    private String avatarUrl;
}
