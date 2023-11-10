package com.example.wit.entities.contest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private LocalDateTime startDatetime;
    @NotNull
    private LocalDateTime endDatetime;
    private Integer durationMilliseconds;
    @NotBlank
    private String editorialUrl;
    Set<Long> problemIds;
}
