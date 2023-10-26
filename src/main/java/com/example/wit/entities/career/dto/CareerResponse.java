package com.example.wit.entities.career.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CareerResponse {
    private Short id;
    private String name;
    private String description;
}
