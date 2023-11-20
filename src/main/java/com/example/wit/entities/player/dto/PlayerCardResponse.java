package com.example.wit.entities.player.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PlayerCardResponse {
    private Short id;
    private String suit;
    private String rank;
    private String rarity;
    private String description;
    private Integer pointConversion;
    private Short quantity;
}
