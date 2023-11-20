package com.example.wit.entities.problem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProblemRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String statement;
    @NotNull
    private Integer memoryLimit;
    @NotNull
    private Short timeLimit;
    @NotBlank
    private String sourceUrl;
    Set<Short> tagIds;
}
