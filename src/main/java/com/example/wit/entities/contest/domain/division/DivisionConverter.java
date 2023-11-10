package com.example.wit.entities.contest.domain.division;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class DivisionConverter implements AttributeConverter<Division, Character> {
    @Override
    public Character convertToDatabaseColumn(Division division) {
        return (division.id() != null ? division.id() : null);
    }

    @Override
    public Division convertToEntityAttribute(Character divisionId) {
        return Stream.of(Division.values())
                .filter(division -> division.id().equals(divisionId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
