package com.example.wit.entities.card.domain;

import com.example.wit.entities.card.domain.rarity.Rarity;
import com.example.wit.entities.player.domain.PlayerCard;
import com.example.wit.entities.reward.domain.RewardCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 10, nullable = false)
    private String suit;
    @Column(length = 5, nullable = false)
    private String rank;
    @Column(nullable = false)
    Rarity rarity;
    private String description;
    private Integer pointConversion;
    @OneToMany(mappedBy = "card")
    Set<PlayerCard> players;
    @OneToMany(mappedBy = "card")
    Set<RewardCard> cards;
}
