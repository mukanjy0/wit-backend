package com.example.wit.challenge.domain.quest;

import com.example.wit.challenge.domain.Challenge;
import com.example.wit.problem.domain.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Quest {
    @Id
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String details;
    @Column(length = 20, nullable = false)
    private String difficulty;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    Challenge challenge;
    @OneToMany(mappedBy = "quest")
    Set<QuestPlayer> players;
    @ManyToMany
    Set<Problem> problems;
}
