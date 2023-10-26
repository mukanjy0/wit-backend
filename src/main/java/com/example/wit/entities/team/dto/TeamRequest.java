package com.example.wit.entities.team.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TeamRequest {
    @Size(max = 24) @NonNull
    private String name;
    private Integer rating;
}
