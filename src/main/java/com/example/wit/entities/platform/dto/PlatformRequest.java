package com.example.wit.entities.platform.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlatformRequest {
    @Size(max = 24) @NonNull
    private String name;
    private String url;
}
