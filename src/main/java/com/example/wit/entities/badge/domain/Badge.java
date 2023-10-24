package com.example.wit.entities.badge.domain;

import com.example.wit.entities.player.domain.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 20, nullable = false)
    private String rarity;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 255, nullable = false)
    private String imageUrl;
    @ManyToMany(mappedBy = "badges")
    Set<Player> players;
}
