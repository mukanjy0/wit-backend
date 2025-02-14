package com.example.wit.entities.career.domain;

import com.example.wit.entities.challenge.domain.Challenge;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.university.domain.University;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 255)
    private String description;
    @ManyToMany(mappedBy = "careers")
    Set<University> universities;
    @OneToMany(mappedBy = "career")
    Set<Player> players;
    @ManyToMany(mappedBy = "careers")
    Set<Challenge> challenges;
}
