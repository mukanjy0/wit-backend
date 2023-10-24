package com.example.wit.cardtype.domain;

import com.example.wit.player.domain.PlayerCardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String suit;
    @Column(length = 20, nullable = false)
    private String rarity;
    @Column(length = 200)
    private String description;
    private String imageUrl;
    @OneToMany(mappedBy = "cardType")
    Set<PlayerCardType> players;
}
