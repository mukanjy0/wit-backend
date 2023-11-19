package com.example.wit.entities.card.dto;


import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CardResponse {
    private String suit;
    private String rank;
    private String rarity;
    private String description;
    private Integer pointConversion;
}
