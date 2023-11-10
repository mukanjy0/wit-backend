package com.example.wit.entities.submission.dto;

import com.example.wit.entities.problem.domain.Problem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SubmissionRequest {
    @NotNull
    private Long problemId;
    @NotNull
    private Long playerId;
    @NotNull
    private LocalDateTime dateTime;
    private String result;
    @NotNull
    private Boolean inPractice;
    @NotBlank
    private String code;
}
