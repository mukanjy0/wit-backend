package com.example.wit.entities.player.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter @RequiredArgsConstructor
public class PlayerSignUp {
    final private String username;
    final private Integer points;
    final private Integer rating;
    final private Short currentCategory;
    final private Short bestCategory;
    final private Short careerId;
    final private Short universityId;
    final private Long teamId;
}
