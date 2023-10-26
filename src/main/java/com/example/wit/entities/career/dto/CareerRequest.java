package com.example.wit.entities.career.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CareerRequest {
    private Short id;
    @Size(max = 50)
    @NotBlank
    private String name;
    @Size(max = 255)
    private String description;
}
