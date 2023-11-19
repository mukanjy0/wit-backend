package com.example.wit.entities.player.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PlayerCardResponse {
    private Short cardId;
    private Integer quantity;
}
