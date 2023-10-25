package com.example.wit.entities.player.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerUpdate {
    private Integer points;
    private Integer rating;
    private Short currentCategory;
    private Short bestCategory;
    private Short careerId;
    private Short universityId;
    private Long teamId;
}
