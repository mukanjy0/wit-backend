package com.example.wit.entities.card.domain.rarity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RarityConverter implements AttributeConverter<Rarity, String> {
    @Override
    public String convertToDatabaseColumn(Rarity rarity) {
        return rarity.name();
    }

    @Override
    public Rarity convertToEntityAttribute(String rarityName) {
        return Stream.of(Rarity.values())
                .filter(rarity -> rarity.name().equals(rarityName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
