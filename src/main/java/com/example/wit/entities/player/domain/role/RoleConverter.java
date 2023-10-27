package com.example.wit.entities.player.domain.role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null)
            return null;

        return role.role();
    }

    @Override
    public Role convertToEntityAttribute(String name) {
        if (name == null)
            return null;

        return Stream.of(Role.values())
                .filter(r -> r.role().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
