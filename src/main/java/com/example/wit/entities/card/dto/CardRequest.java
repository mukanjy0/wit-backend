package com.example.wit.entities.card.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CardRequest {
    @Size(max = 10)
    @NotBlank
    private String suit;
    @Size(max = 5)
    @NotBlank
    private String rank;
    @NotBlank
    private String rarity;
    private String description;
    private Integer pointConversion;
}
