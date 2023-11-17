package com.example.wit.entities.contest.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContestPlayerResponse {
    private Long id;
    private String username;
    private Short categoryId;
    private String avatarUrl;
}
