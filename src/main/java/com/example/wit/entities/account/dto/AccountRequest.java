package com.example.wit.entities.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AccountRequest {
    @Size(max = 24) @NotBlank
    private String handle;
    @NotNull
    private Short platformId;
    @NotNull
    private Long playerId;
    @NotNull
    private Integer rating;
    @NotNull
    private String url;
}
