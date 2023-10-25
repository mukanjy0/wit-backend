package com.example.wit.entities.university.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UniversityRequest {
    @Size(max = 6)
    private String acronym;
    @Size(max = 50)
    @NotBlank
    private String fullName;
}
