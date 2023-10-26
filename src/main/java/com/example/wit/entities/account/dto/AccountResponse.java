package com.example.wit.entities.account.dto;

import com.example.wit.entities.platform.domain.Platform;
import com.example.wit.entities.player.domain.Player;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AccountResponse {
    private Long id;
    private String handle;
    private Short platformId;
    private Long playerId;
    private Integer rating;
    private String url;
}
