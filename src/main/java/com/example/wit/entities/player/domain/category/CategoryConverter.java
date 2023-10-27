package com.example.wit.entities.player.domain.category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, Short> {
    @Override
    public Short convertToDatabaseColumn(Category category) {
        if (category == null)
            return null;

        return category.id();
    }

    @Override
    public Category convertToEntityAttribute(Short id) {
        if (id == null)
            return null;

        return Stream.of(Category.values())
                .filter(c -> c.id().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
