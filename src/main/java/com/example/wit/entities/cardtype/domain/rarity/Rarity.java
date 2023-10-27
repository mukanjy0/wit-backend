package com.example.wit.entities.cardtype.domain.rarity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum Rarity {
    COMMON("Common", "b0b0b0"),
    UNCOMMON("Uncommon", "1eff00"),
    RARE("Rare", "0070dd"),
    EPIC("Epic", "a335ee"),
    LEGENDARY("Legendary", "ff8000"),
    MYTHICAL("Mythical", "ff66cc"),
    TRASCENDENT("Transcendent", "4d4dff"),
    GODLIKE("Godlike", "c41f3b");
    private String rarity;
    private String color;
}