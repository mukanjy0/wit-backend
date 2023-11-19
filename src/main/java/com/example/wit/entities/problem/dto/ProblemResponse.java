package com.example.wit.entities.problem.dto;

import com.example.wit.entities.tag.domain.Tag;
import lombok.*;

import java.util.Set;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProblemResponse {
    private Long id;
    private String statement;
    private Integer memoryLimit;
    private Short timeLimit;
    private String sourceUrl;
}
