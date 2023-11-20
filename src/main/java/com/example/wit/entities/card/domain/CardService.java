package com.example.wit.entities.card.domain;

import com.example.wit.entities.card.domain.rarity.Rarity;
import com.example.wit.entities.card.dto.CardRequest;
import com.example.wit.entities.card.dto.CardResponse;
import com.example.wit.entities.card.utils.CardUtils;
import com.example.wit.exceptions.ElementAlreadyExistsException;
import com.example.wit.exceptions.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CardService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CardRepository repository;

    public List<CardResponse> read() {
        return repository
                .findAll()
                .stream().map(card -> mapper.map(card, CardResponse.class))
                .collect(Collectors.toList());

    }

    public CardResponse read(Short id) {
        Optional<Card> card  = repository.findById(id);
        if (card.isEmpty()) {
            throw ElementNotFoundException.createWith("Card", id.toString());
        }

        return mapper.map(card.get(), CardResponse.class);
    }

    public void create(CardRequest card) {
        String suit = card.getSuit();
        String rank = card.getRank();
        Optional<Card> original = repository.findCardBySuitAndRank(suit, rank);
        if (original.isPresent()) {
            throw ElementAlreadyExistsException.createWith(suit + "-" + rank, "(suit, rank)");
        }

        String rarityName = card.getRarity();
        if (Stream.of(Rarity.values())
                .filter(rarity -> rarity.name().equals(rarityName))
                .toList()
                .isEmpty()) {
            throw ElementNotFoundException.createWith("Rarity", rarityName);
        }

        repository.save(mapper.map(card, Card.class));
    }

    public void update(Short id, CardRequest card) {
        Optional<Card> original = repository.findById(id);
        if (original.isEmpty()) {
            throw ElementNotFoundException.createWith("Card", id.toString());
        }

        Card previous = original.get();
        String suit = previous.getSuit();
        String rank = previous.getRank();

        Card updated = CardUtils.updateCard(previous, card);
        String newSuit = updated.getSuit();
        String newRank = updated.getRank();

        if (!suit.equals(newSuit) || !rank.equals(newRank)) {
            throw ElementAlreadyExistsException.createWith(suit + "-" + rank, "(suit, rank)");
        }

        repository.save(updated);
    }

    public void delete(Short id) {
        Optional<Card> card = repository.findById(id);
        if (card.isEmpty()) {
            throw ElementNotFoundException.createWith("Card", id.toString());
        }

        repository.deleteById(id);
    }
}
