package com.example.wit.entities.card.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Short> {
    Optional<Card> findCardBySuitAndRank(String suit, String rank);
}
