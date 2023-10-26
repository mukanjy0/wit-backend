package com.example.wit.entities.team.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TeamResponse {
    private Long id;
    private String name;
//    private Integer rank;
    private Integer rating;
}
