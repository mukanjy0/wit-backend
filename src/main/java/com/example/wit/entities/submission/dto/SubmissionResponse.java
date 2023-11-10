package com.example.wit.entities.submission.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SubmissionResponse {
    private Long id;
    private Long problemId;
    private Long playerId;
    private LocalDateTime dateTime;
    private String result;
    private Boolean inPractice;
    private String code;
}
