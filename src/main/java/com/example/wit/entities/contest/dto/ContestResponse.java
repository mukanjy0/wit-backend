package com.example.wit.entities.contest.dto;

import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.problem.dto.ProblemResponse;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContestResponse {
    private Long id;
    private Character division;
    private String title;
    private String description;
    private LocalDateTime startDatetime;;
    private LocalDateTime endDatetime;
    private Integer durationMilliseconds;
    private String editorialUrl;
    ContestPlayerResponse player;
    Set<ProblemResponse> problems;
}
