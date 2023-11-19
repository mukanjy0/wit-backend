package com.example.wit.entities.card.utils;

import com.example.wit.entities.card.domain.Card;
import com.example.wit.entities.card.domain.rarity.Rarity;
import com.example.wit.entities.card.dto.CardRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class CardUtils {
    public static Card updateCard(Card original, CardRequest toUpdate) {
        String suit = toUpdate.getSuit();
        if (suit != null) original.setSuit(suit);

        String rank = toUpdate.getRank();
        if (rank != null) original.setRank(rank);

        String rarityName = toUpdate.getRarity();
        if (rarityName != null) {
            Rarity rarity = Stream.of(Rarity.values())
                    .filter(rar -> rar.name().equals(rarityName))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            original.setRarity(rarity);
        }

        String description = toUpdate.getDescription();
        if (description != null) original.setDescription(description);

        Integer pointConversion = toUpdate.getPointConversion();
        if (pointConversion != null) original.setPointConversion(pointConversion);

        return original;
    }
}
