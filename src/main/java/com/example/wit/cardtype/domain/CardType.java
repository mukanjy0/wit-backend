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
    @Column(length = 20)
    private String name;
    @Id
    @Column(length = 20)
    private String suit;
    @Column(length = 20)
    private String rarity;
    @Column(length = 200)
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @OneToMany(mappedBy = "cardType")
    Set<PlayerCardType> players;
}
