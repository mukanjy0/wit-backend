package com.example.wit.entities.player.domain.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter @NoArgsConstructor @AllArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;
}
