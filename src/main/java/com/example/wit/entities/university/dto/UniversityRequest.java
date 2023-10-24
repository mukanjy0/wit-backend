package com.example.wit.entities.university.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter @RequiredArgsConstructor
public class UniversityRequest {
    @Size(max = 6)
    final private String acronym;
    @Size(max = 50)
    @NotBlank
    final private String fullName;
}
