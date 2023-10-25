package com.example.wit.entities.university.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UniversityResponse {
     private Short id;
     private String acronym;
     private String fullName;
}
