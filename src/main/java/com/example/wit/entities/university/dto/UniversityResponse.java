package com.example.wit.entities.university.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter @RequiredArgsConstructor
public class UniversityResponse {
    final private Short id;
    final private String acronym;
    final private String fullName;
}
