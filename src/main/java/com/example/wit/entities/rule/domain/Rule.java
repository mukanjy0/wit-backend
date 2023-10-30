package com.example.wit.entities.rule.domain;

import com.example.wit.entities.challenge.domain.Challenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String details;
    @ManyToMany(mappedBy = "rules")
    Set<Challenge> challenges;
}
