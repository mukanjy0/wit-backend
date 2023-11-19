package com.example.wit.entities.contest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContestRequest {
    @NotNull
    private Character division;
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private LocalDateTime startDatetime;
    @NotNull
    private LocalDateTime endDatetime;
    private Integer durationMilliseconds;
    @NotBlank
    private String editorialUrl;
    @NotNull
    private Long playerId;
    Set<Long> problemIds;
}
