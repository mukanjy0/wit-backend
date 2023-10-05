package com.example.wit.badge.domain;

import com.example.wit.player.domain.Player;
import com.example.wit.rarity.domain.Rarity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(
        name = "BadgeSeq",
        sequenceName = "BADGE_SEQ",
        initialValue = 0,
        allocationSize = 1
)
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BadgeSeq")
    private Short id;
    @ManyToOne(optional = false)
    Rarity rarity;
    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 255)
    private String imageUrl;
    @ManyToMany(mappedBy = "badges")
    Set<Player> players;
}
