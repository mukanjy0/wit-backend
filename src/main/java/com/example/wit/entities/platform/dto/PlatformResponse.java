package com.example.wit.entities.platform.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlatformResponse {
    private Short id;
    private String name;
    private String url;
}
