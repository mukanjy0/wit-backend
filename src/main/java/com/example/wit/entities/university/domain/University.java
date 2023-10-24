package com.example.wit.entities.university.domain;

import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.challenge.domain.Challenge;
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
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 6)
    private String acronym;
    @Column(length = 50, nullable = false)
    private String fullName;
    @ManyToMany
    Set<Career> careers;
    @OneToMany(mappedBy = "university")
    Set<Player> players;
    @ManyToMany(mappedBy = "universities")
    Set<Challenge> challenges;
}
